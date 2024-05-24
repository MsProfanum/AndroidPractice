package com.example.di

import com.example.api.api_client.OpenMeteoApiService
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOpenMeteoApiService(): OpenMeteoApiService {
        return OpenMeteoApiService.create()
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(apiClient: OpenMeteoApiService): WeatherRepository {
        return WeatherRepositoryImpl(apiClient)
    }
}