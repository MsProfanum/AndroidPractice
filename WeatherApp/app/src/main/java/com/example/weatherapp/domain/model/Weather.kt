package com.example.weatherapp.domain.model

import com.example.weatherapp.domain.util.WeatherCondition

data class Weather(
    val location: String,
    val temperature: Double,
    val condition: WeatherCondition
) {
    companion object {
        fun toCondition(num: Int): WeatherCondition {
            return when (num) {
                0 -> WeatherCondition.clear
                1, 2, 3, 45, 48 -> WeatherCondition.cloudy
                51, 53, 55, 56, 57, 61, 63, 65, 66, 67, 80, 81, 82, 95, 96, 99 -> WeatherCondition.rainy
                71, 73, 75, 77, 85, 86 -> WeatherCondition.snowy
                else -> WeatherCondition.unknown
            }
        }
    }
}
