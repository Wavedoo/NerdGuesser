package com.example.nerdguesser.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.update

class GuessingGameViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(GuessingGameUiState())
    val uiState: StateFlow<GuessingGameUiState> = _uiState.asStateFlow()

    private lateinit var correctAnswer: String

    var userGuess by mutableStateOf("")
        private set

    //Mock functions
    fun getAnswerDetails(){
        correctAnswer = "Frieren"
        _uiState.value = GuessingGameUiState(correctAnswer = correctAnswer)
    }

    //State functions
    fun defaultValues(){

    }

    fun updateGuess(guess: String){
        userGuess = guess
    }

    fun checkUserGuess(){
        val correct = userGuess.equals(correctAnswer, ignoreCase = true)
        val gameOver = _uiState.value.remainingGuesses == 1
        addGuess(correct)
        if (correct){
            _uiState.update {
                it.copy(isCorrect = true, isGameOver = true)
            }
        }else{
            _uiState.update {
                it.copy(
                    remainingGuesses = it.remainingGuesses.dec(),
                    hintsShown = it.hintsShown.inc(),
                    isGameOver = gameOver
                )
            }
        }

    }

    fun correctAnswer(){

    }
    fun wrongAnswer() {

    }
    private fun addGuess(correct: Boolean){

        val listText = if (correct)
            "✅ $userGuess"
        else if(userGuess.isNotEmpty())
            "❌ $userGuess"
        else
            "❌ Skipped"

        val newGuesses = _uiState.value.guesses + listText
        _uiState.update { it ->
            it.copy(guesses = newGuesses)
        }
    }

    init {
        getAnswerDetails()
    }
}