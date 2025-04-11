package com.example.nerdguesser.viewmodel

import androidx.compose.runtime.mutableStateListOf
import com.example.nerdguesser.R
import com.example.nerdguesser.model.classes.Hints
import com.example.nerdguesser.view.components.buttons.Status


/*
Related: https://developer.android.com/develop/ui/compose/state#state-in-composables
Something about mutableListOf not retriggering recomposition
*/

//TODO: change hints, correctAnswer, and images into AnswerData
data class GuessingGameUiState(
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
    val guessResults: MutableList<Status> = mutableStateListOf<Status>(Status.NotGuessed, Status.Disabled, Status.Disabled, Status.Disabled, Status.Disabled, Status.Disabled),
    val correctAnswer: String = "",
    val hints: Hints = Hints(),
    val currentFrame: Int = 1,
    val currentImage: Int = R.drawable.frieren_village_1,
    val isCorrect: Boolean = false,
    val isGameOver: Boolean = false,
    val gameNumber: Int = 1,
    val images: List<Int> = listOf<Int>(
        R.drawable.frieren_village_1,
        R.drawable.frieren_ring_2,
        R.drawable.himmel_dead_3,
        R.drawable.ubel_4,
        R.drawable.fern_stark_5,
        R.drawable.frieren_landscape_6
    ),
)