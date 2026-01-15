package com.example.bulbmanager.presentier

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bulbmanager.R
import com.example.bulbmanager.appComponent
import com.example.bulbmanager.databinding.MainFragmentBinding
import com.example.bulbmanager.di.viewModel.ViewModelFactory
import dev.androidbroadcast.vbpd.viewBinding
import javax.inject.Inject

class MainFragment : Fragment(R.layout.main_fragment) {

    val binding: MainFragmentBinding by viewBinding(MainFragmentBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private fun render(state: ViewModelState) {
        with(binding) {

            progressBar.isVisible = state.isLoading
            button.isEnabled = !state.isLoading
            seekBar.isEnabled = !state.isLoading
            spinner.isEnabled = !state.isLoading

            if (state.isLoading) {
                return
            }

            if (state.state) {
                indicator.setImageResource(android.R.drawable.button_onoff_indicator_on)
            } else {
                indicator.setImageResource(android.R.drawable.button_onoff_indicator_off)
            }

            if (seekBar.progress != state.brightness) {
                seekBar.progress = state.brightness
            }

            val adapter = spinner.adapter
            for (i in 0 .. adapter.count) {
                if (adapter.getItem(i).toString().lowercase() == state.color) {
                    if (spinner.selectedItemPosition != i) {
                        spinner.setSelection(i)
                    }

                    break
                }
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Цепляем цвета из ресурсов, так написано в доках андроеда
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.colors,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            binding.spinner.adapter = adapter
        }

        binding.button.setOnClickListener {
            viewModel.toggle()
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                if (fromUser) {
                    viewModel.setBrightness(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Skip
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Skip
            }

        })

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val color = parent?.getItemAtPosition(position).toString().lowercase()
                viewModel.setColor(color)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Skip
            }
        }

        viewModel.state.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    override fun onAttach(context: Context) {
        val component = context.applicationContext.appComponent

        component.inject(this)

        super.onAttach(context)
    }
}