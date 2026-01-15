package com.example.bulbproject.presentier

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bulbproject.domain.ToggleBulbUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val toggleBulbUseCase: ToggleBulbUseCase,
): ViewModel() {

    fun switch() {
        viewModelScope.launch {
            toggleBulbUseCase()
        }
    }

}