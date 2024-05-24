package com.example.weatherapp.presentation.weather

sealed class WeatherEvent {
    data class GetWeather(val name: String) : WeatherEvent()
    object RefreshWeather : WeatherEvent()
}