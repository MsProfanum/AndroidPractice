package com.example.tictactoe.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tictactoe.presentation.view_model.GameEvent
import com.example.tictactoe.presentation.view_model.GameViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TileGrid(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel
) {
    val state = viewModel.state.value

    Scaffold(modifier = modifier.padding(16.dp)) {
        if (state.playerWon) {
            ShowAlert(title = "Player ${state.turn.sign} won!", onClick = {
                viewModel.onEvent(GameEvent.NewGame)
            })
        } else if (state.draw) {
            ShowAlert(title = "There is a draw!", onClick = {
                viewModel.onEvent(GameEvent.NewGame)
            })
        }

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Current player turn: ${state.turn.sign}")
            Spacer(modifier = Modifier.height(64.dp))
            TileRow(tilesTexts = state.tiles.subList(0, 3), rowNumber = 0, onClick = {
                viewModel.onEvent(GameEvent.TapTile(it))
            })
            Divider(
                modifier = modifier
                    .fillMaxWidth()
                    .width(2.dp)
            )
            TileRow(tilesTexts = state.tiles.subList(3, 6), rowNumber = 1, onClick = {
                viewModel.onEvent(GameEvent.TapTile(it))
            })
            Divider(
                modifier = modifier
                    .width(2.dp)
                    .fillMaxWidth()
            )
            TileRow(tilesTexts = state.tiles.subList(6, 9), rowNumber = 2, onClick = {
                viewModel.onEvent(GameEvent.TapTile(it))

            })
        }
    }

}