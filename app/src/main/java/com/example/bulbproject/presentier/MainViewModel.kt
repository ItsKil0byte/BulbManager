package com.example.bulbproject.presentier

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bulbproject.domain.GetInfoUseCase
import com.example.bulbproject.domain.SetBrightnessUseCase
import com.example.bulbproject.domain.SetColorUseCase
import com.example.bulbproject.domain.ToggleBulbUseCase
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

/*
* Тут была попытка всегда держать актуальную инфу в приложении :")
* По итогу вышло как по мне костыльно, да и к тому же с багами, но мне уже тяжело что
* либо тут отлаживать, поэтому оставлю так.
*/

data class ViewModelState(
    val state: Boolean = false,
    val brightness: Int = 0,
    val color: String = "red",
    val isLoading: Boolean = true // Костыль чтобы избежать состояния гонки
)

class MainViewModel @Inject constructor(
    private val getInfoUseCase: GetInfoUseCase,
    private val toggleBulbUseCase: ToggleBulbUseCase,
    private val setColorUseCase: SetColorUseCase,
    private val setBrightnessUseCase: SetBrightnessUseCase
) : ViewModel() {

    private val _state = MutableLiveData(ViewModelState())
    val state: LiveData<ViewModelState> = _state

    init {
        viewModelScope.launch {
            try {
                val viewModelState = getInfoUseCase()

                _state.value = ViewModelState(
                    state = viewModelState.state,
                    brightness = viewModelState.brightness,
                    color = viewModelState.color,
                    isLoading = false
                )
            } catch (e: Exception) {
                // Всё, пиздец, не работает.
            }
        }
    }

    fun toggle() {
        viewModelScope.launch {
            _state.value = _state.value?.copy(state = toggleBulbUseCase())
        }
    }

    fun setColor(color: String) {
        // Игнор лишних срабатываний
        if (_state.value?.isLoading == true) {
            return
        }

        _state.value = _state.value?.copy(color = color)
        viewModelScope.launch {
            setColorUseCase(color)
        }
    }

    fun setBrightness(value: Int) {
        // Игнор лишних срабатываний
        if (_state.value?.isLoading == true) {
            return
        }

        _state.value = _state.value?.copy(brightness = value)
        viewModelScope.launch {
            setBrightnessUseCase(value)
        }
    }

}