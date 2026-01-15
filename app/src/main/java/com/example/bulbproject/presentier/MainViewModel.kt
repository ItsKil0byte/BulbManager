package com.example.bulbproject.presentier

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bulbproject.domain.SetBrightnessUseCase
import com.example.bulbproject.domain.SetColorUseCase
import com.example.bulbproject.domain.ToggleBulbUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val toggleBulbUseCase: ToggleBulbUseCase,
    private val setColorUseCase: SetColorUseCase,
    private val setBrightnessUseCase: SetBrightnessUseCase
): ViewModel() {

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> = _state

    fun toggle() {
        viewModelScope.launch {
            _state.value = toggleBulbUseCase()
        }
    }

    fun setColor(color: String) {
        viewModelScope.launch {
            setColorUseCase(color)
        }
    }

    fun setBrightness(value: Int) {
        viewModelScope.launch {
            setBrightnessUseCase(value)
        }
    }

}