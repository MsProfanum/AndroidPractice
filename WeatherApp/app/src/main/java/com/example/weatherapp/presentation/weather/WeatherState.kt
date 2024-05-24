package com.example.weatherapp.presentation.weather

import com.example.weatherapp.domain.model.Weather

data class WeatherState(
    val lastUpdated: Long,
    val weather: Weather?,
    val temperatureUnits: TemperatureUnits
) {
    companion object {
        fun empty() = WeatherState(
            lastUpdated = System.currentTimeMillis(),
            weather = null,
            temperatureUnits = TemperatureUnits.celsius
        )
    }
}

enum class TemperatureUnits {
    fahrenheit,
    celsius
}