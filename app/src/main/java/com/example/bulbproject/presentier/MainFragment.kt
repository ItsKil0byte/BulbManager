package com.example.bulbproject.presentier

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bulbproject.R
import com.example.bulbproject.appComponent
import com.example.bulbproject.databinding.MainFragmentBinding
import com.example.bulbproject.di.viewModel.ViewModelFactory
import dev.androidbroadcast.vbpd.viewBinding
import javax.inject.Inject

class MainFragment: Fragment(R.layout.main_fragment) {

    val binding: MainFragmentBinding by viewBinding(MainFragmentBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btn.setOnClickListener {
            viewModel.switch()
        }
    }

    override fun onAttach(context: Context) {
        var component = context.applicationContext.appComponent

        component.inject(this)

        super.onAttach(context)
    }
}