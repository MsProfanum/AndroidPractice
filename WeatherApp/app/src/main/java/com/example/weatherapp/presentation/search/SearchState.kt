package com.example.weatherapp.presentation.search

data class SearchState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
