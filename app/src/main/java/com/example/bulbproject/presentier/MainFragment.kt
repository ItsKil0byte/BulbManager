package com.example.bulbproject.presentier

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bulbproject.R
import com.example.bulbproject.appComponent
import com.example.bulbproject.databinding.MainFragmentBinding
import com.example.bulbproject.di.viewModel.ViewModelFactory
import dev.androidbroadcast.vbpd.viewBinding
import javax.inject.Inject

class MainFragment : Fragment(R.layout.main_fragment) {

    val binding: MainFragmentBinding by viewBinding(MainFragmentBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

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

        binding.btn.setOnClickListener {
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
    }

    override fun onAttach(context: Context) {
        var component = context.applicationContext.appComponent

        component.inject(this)

        super.onAttach(context)
    }
}