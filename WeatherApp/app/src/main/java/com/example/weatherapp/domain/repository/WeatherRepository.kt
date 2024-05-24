package com.example.weatherapp.domain.repository

import com.example.api.dto.WeatherDto
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.util.WeatherCondition

interface WeatherRepository {
    suspend fun getWeather(city: String): Weather?
}