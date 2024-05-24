package com.example.weatherapp.domain.repository

import com.example.api.api_client.OpenMeteoApiService
import com.example.weatherapp.domain.model.Weather

class WeatherRepositoryImpl(
    val apiClient: OpenMeteoApiService
) : WeatherRepository {

    override suspend fun getWeather(city: String): Weather? {
        val location = apiClient.locationSearch(city) ?: return null

        val weatherDto = apiClient.getWeatherDto(
            latitude = location.latitude,
            longitude = location.longitude
        ) ?: return null

        return Weather(
            location = location.name,
            temperature = weatherDto.temperature,
            condition = Weather.toCondition(weatherDto.weatherCode.toInt())
        )
    }
}