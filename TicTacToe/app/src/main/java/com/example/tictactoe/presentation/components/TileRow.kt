package com.example.tictactoe.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun TileRow(
    modifier: Modifier = Modifier,
    tilesTexts: List<String>,
    rowNumber: Int,
    onClick: (index: Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (i in tilesTexts.indices) {
            val tileIndex = i + (tilesTexts.size * rowNumber)

            Tile(
                fillText = tilesTexts[i],
                onClick = { onClick(tileIndex) },
            )
            if (i != tilesTexts.size - 1) VerticalDivider()
        }
    }

}