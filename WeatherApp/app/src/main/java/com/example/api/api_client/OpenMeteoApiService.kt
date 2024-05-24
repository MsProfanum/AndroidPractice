package com.example.api.api_client

import com.example.api.dto.LocationDto
import com.example.api.dto.WeatherDto
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

interface OpenMeteoApiService {
    suspend fun locationSearch(name: String): LocationDto?

    suspend fun getWeatherDto(latitude: Double, longitude: Double): WeatherDto?

    companion object {
        fun create(): OpenMeteoApiService {
            return OpenMeteoApiClient(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation) {
                        json()
                    }
                }
            )
        }
    }
}