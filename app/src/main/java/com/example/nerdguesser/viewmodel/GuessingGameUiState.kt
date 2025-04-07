package com.example.nerdguesser.viewmodel

import com.example.nerdguesser.model.classes.Hints


/*
Related: https://developer.android.com/develop/ui/compose/state#state-in-composables
Something about mutableListOf not retriggering recomposition
*/
data class GuessingGameUiState(
    val remainingGuesses: Int = 6,
    val hintsShown: Int = 0,
    val guesses: List<String> = listOf(),
    val correctAnswer: String = "",
    val hints: Hints = Hints(),
    val currentFrame: Int = 1,
    val isCorrect: Boolean = false,
    val isGameOver: Boolean = false
)