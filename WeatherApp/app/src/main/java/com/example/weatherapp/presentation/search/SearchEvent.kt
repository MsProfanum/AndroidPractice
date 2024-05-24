package com.example.weatherapp.presentation.search

import androidx.compose.ui.focus.FocusState

sealed class SearchEvent {
    data class EditSearch(val text: String) : SearchEvent()
    data class ChangeFocus(val focusState: FocusState) : SearchEvent()
}