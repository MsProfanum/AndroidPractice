package com.example.weatherapp.presentation.weather.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.presentation.weather.TemperatureUnits
import com.example.weatherapp.presentation.weather.WeatherEvent
import com.example.weatherapp.presentation.weather.WeatherViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeatherPopulated(
    modifier: Modifier = Modifier,
    weather: Weather,
    units: TemperatureUnits,
) {
    val viewModel: WeatherViewModel = hiltViewModel()
    val refreshing = viewModel.isRefreshing.collectAsState().value
    val pullRefreshState = rememberPullRefreshState(
        refreshing,
        onRefresh = { viewModel.onEvent(WeatherEvent.RefreshWeather) })

    Box(
        modifier = modifier
            .pullRefresh(pullRefreshState)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            Text(text = weather.location)
            Text("${weather.temperature} $units")
        }
        PullRefreshIndicator(refreshing = viewModel.isRefreshing.value, state = pullRefreshState)
    }
}