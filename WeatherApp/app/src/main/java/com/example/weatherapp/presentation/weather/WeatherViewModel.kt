package com.example.weatherapp.presentation.weather

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    val repository: WeatherRepository
) : ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val _state = mutableStateOf<WeatherState>(WeatherState.empty())
    val state: State<WeatherState> = _state

    fun onEvent(event: WeatherEvent) {
        when (event) {
            is WeatherEvent.GetWeather -> {
                viewModelScope.launch {
                    val weather = repository.getWeather(event.name)

                    _state.value = state.value.copy(
                        lastUpdated = System.currentTimeMillis(),
                        weather = weather
                    )
                }
            }

            is WeatherEvent.RefreshWeather -> {
                viewModelScope.launch {
                    val weather = repository.getWeather(state.value.weather?.location ?: "")

                    _state.value = state.value.copy(
                        lastUpdated = System.currentTimeMillis(),
                        weather = weather
                    )
                }
            }
        }
    }
}