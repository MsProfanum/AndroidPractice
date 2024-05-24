package com.example.weatherapp.presentation.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.presentation.weather.WeatherEvent
import com.example.weatherapp.presentation.weather.WeatherViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel(),
    weatherViewModel: WeatherViewModel = hiltViewModel()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        BasicTextField(
            value = searchViewModel.state.value.text,
            onValueChange = {
                searchViewModel.onEvent(SearchEvent.EditSearch(it))
            }
        )
        IconButton(onClick = {
            weatherViewModel.onEvent(WeatherEvent.GetWeather(searchViewModel.state.value.text))
        }) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        }
    }
}