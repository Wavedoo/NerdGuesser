package com.example.nerdguesser.viewmodel

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.ImageBitmap
import com.example.nerdguesser.R
import com.example.nerdguesser.model.classes.GameData
import com.example.nerdguesser.model.classes.Hints
import com.example.nerdguesser.model.utils.GameDataUtil
import com.example.nerdguesser.view.components.buttons.Status
import com.google.firestore.admin.v1.Index


/*
Related: https://developer.android.com/develop/ui/compose/state#state-in-composables
Something about mutableListOf not retriggering recomposition
*/

//TODO: change hints, correctAnswer, and images into AnswerData
data class NewGuessingGameUiState(
    val gameData: GameData = GameDataUtil.test,

    //non-firestore
    val remainingGuesses: Int = 6,
    val hintsShown: Int = 0,
    val guesses: List<String> = listOf(),
    /*
    TODO: Figure out which way is better
    MutableList(6) {
        if(it == 0) {
            Status.NotGuessed
        }else{
            Status.Disabled
        }
    }
    */
    val guessResults: MutableList<Status> = mutableStateListOf(Status.NotGuessed, Status.Disabled, Status.Disabled, Status.Disabled, Status.Disabled, Status.Disabled),
    val currentFrame: Int = 1,
    val isCorrect: Boolean = false,
    val isGameOver: Boolean = false,
    val imageIndex: Int = 0,
    val images: List<ImageBitmap> = emptyList()
)