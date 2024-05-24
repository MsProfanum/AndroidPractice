package com.example.weatherapp.presentation.utils

sealed class Routes(val route: String) {
    object WeatherScreen : Routes("weather_screen")
    object SettingsScreen : Routes("settings_screeen")
    object SearchScreen : Routes("search_screen")
}