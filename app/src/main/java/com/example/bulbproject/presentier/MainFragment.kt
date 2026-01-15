package com.example.bulbproject.presentier

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
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
    }

    override fun onAttach(context: Context) {
        var component = context.applicationContext.appComponent

        component.inject(this)

        super.onAttach(context)
    }
}