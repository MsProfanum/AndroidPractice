package com.example.tictactoe.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tictactoe.domain.model.Player
import kotlin.random.Random

class GameViewModel : ViewModel() {
    private val _state = mutableStateOf<GameState>(GameState.newGame())
    val state: State<GameState> = _state


    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.TapTile -> {
                var newTiles = state.value.tiles
                if (newTiles[event.tileIndex].isNotEmpty()) {
                    // TODO(katarzyans): Add SnackBar informing user that this tile cannot be changed
                    return
                }

                newTiles[event.tileIndex] = state.value.turn.sign

                val didPlayerWon = _didPlayerWon(newTiles)
                if (didPlayerWon) {
                    _state.value = state.value.copy(
                        tiles = newTiles,
                        playerWon = true
                    )
                    return
                } else if (!newTiles.contains("") && !didPlayerWon) {
                    _state.value = state.value.copy(
                        tiles = newTiles,
                        draw = true
                    )
                    return
                }

                _state.value = state.value.copy(
                    tiles = newTiles,
                    turn = _getNextPlayer()
                )
            }

            is GameEvent.NewGame -> {
                _state.value = GameState.newGame()
            }
        }
    }

    private fun _didPlayerWon(tiles: List<String>): Boolean {
        val lines: List<List<Int>> = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )

        for (i in lines.indices) {
            val line = lines[i]

            if (tiles[line[0]].isNotEmpty() && tiles[line[0]] == tiles[line[1]] && tiles[line[1]] == tiles[line[2]]) {
                return true
            }
        }

        return false
    }

    private fun _getNextPlayer(): Player {
        return if (_state.value.turn == Player.CROSS) Player.CIRCLE else Player.CROSS
    }
}

data class GameState(
    val tiles: MutableList<String>,
    val playerWon: Boolean = false,
    val draw: Boolean = false,
    val turn: Player,
) {
    companion object {
        fun newGame() = GameState(
            tiles = MutableList(9) { "" },
            turn = if (Random.nextBoolean()) Player.CROSS else Player.CIRCLE,
        )
    }
}

sealed class GameEvent {
    data class TapTile(val tileIndex: Int) : GameEvent()
    object NewGame : GameEvent()
}