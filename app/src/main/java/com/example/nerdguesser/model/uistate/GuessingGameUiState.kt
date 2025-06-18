package com.example.nerdguesser.model.uistate

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.ImageBitmap
import com.example.nerdguesser.model.classes.GameData
import com.example.nerdguesser.model.utils.GameDataUtil
import com.example.nerdguesser.view.components.buttons.Status


/*
Related: https://developer.android.com/develop/ui/compose/state#state-in-composables
Something about mutableListOf not retriggering recomposition
*/

//TODO: Add nullable stuff?
data class GuessingGameUiState(
    val gameData: GameData = GameDataUtil.placeholder,

    //non-firestore
    val remainingGuesses: Int = 6,
    val hintsShown: Int = 0,
    val guesses: List<String> = listOf(),
    val guessResults: MutableList<Status> = mutableStateListOf(Status.NotGuessed, Status.Disabled, Status.Disabled, Status.Disabled, Status.Disabled, Status.Disabled),
    val currentFrame: Int = 1,
    val isCorrect: Boolean = false,
    val isGameOver: Boolean = false,
    val imageIndex: Int = 0,
    val maxFrame: Int = 1,
    val images: List<ImageBitmap> = emptyList(),
    val filteredResults: List<String> = emptyList()
)