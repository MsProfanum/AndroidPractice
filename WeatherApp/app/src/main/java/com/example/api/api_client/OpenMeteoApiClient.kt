package com.example.api.api_client

import com.example.api.dto.LocationDto
import com.example.api.dto.WeatherDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import org.json.JSONObject

class OpenMeteoApiClient(
    private val client: HttpClient
) : OpenMeteoApiService {
    private val BASE_URL_WEATHER = "https://api.open-meteo.com"
    private val BASE_URL_GEOCODING = "https://geocoding-api.open-meteo.com"

    override suspend fun locationSearch(name: String): LocationDto? {
        return try {
            val response = client.get {
                url("$BASE_URL_GEOCODING/v1/search")
                parameter("name", name)
                parameter("count", 1)
            }
            val jsonString = response.bodyAsText()
            val jsonObj = JSONObject(jsonString)
            val location = jsonObj.getJSONArray("results").getJSONObject(0)

            LocationDto(
                id = location.get("id") as Int,
                name = location.get("name") as String,
                latitude = location.get("latitude") as Double,
                longitude = location.get("longitude") as Double
            )
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return null
        }
    }

    override suspend fun getWeatherDto(latitude: Double, longitude: Double): WeatherDto? {
        return try {
            val response = client.get {
                url("$BASE_URL_WEATHER/v1/forecast")
                parameter("latitude", latitude)
                parameter("longitude", longitude)
                parameter("current_weather", true)
            }
            val jsonString = response.bodyAsText()
            val jsonObj = JSONObject(jsonString).getJSONObject("current_weather")

            WeatherDto(
                temperature = jsonObj.get("temperature") as Double,
                weatherCode = jsonObj.get("weathercode") as Int
            )
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return null
        }
    }
}