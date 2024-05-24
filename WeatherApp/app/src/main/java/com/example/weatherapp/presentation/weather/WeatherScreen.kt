package com.example.weatherapp.presentation.weather

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.presentation.search.SearchScreen
import com.example.weatherapp.presentation.utils.Routes
import com.example.weatherapp.presentation.weather.components.WeatherPopulated

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeatherScreen(
    navController: NavController,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Routes.SearchScreen.route)
                },
                containerColor = MaterialTheme.colorScheme.background,
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp)
        ) {
            SearchScreen(navController = navController)
            if (state.weather != null) {
                WeatherPopulated(weather = state.weather, units = state.temperatureUnits)
            } else {
                Text(text = "There is no weather")
            }
        }

    }
}