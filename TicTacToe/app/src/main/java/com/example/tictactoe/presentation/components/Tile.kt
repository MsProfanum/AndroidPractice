package com.example.tictactoe.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Tile(
    modifier: Modifier = Modifier,
    fillText: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clickable(true, onClick = onClick)
            .size(100.dp)
    ) {
        Text(text = fillText, modifier = modifier.align(Alignment.Center))
    }
}