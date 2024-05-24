package com.example.weatherapp.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf<SearchState>(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.EditSearch -> {
                _state.value = state.value.copy(
                    text = event.text
                )
            }

            is SearchEvent.ChangeFocus -> {
                _state.value = state.value.copy(
                    isHintVisible = !state.value.isHintVisible
                )
            }
        }
    }
}