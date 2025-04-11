package com.example.nerdguesser.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import com.example.nerdguesser.model.classes.AnswerData
import com.example.nerdguesser.model.repositories.MockServer
import com.example.nerdguesser.view.components.buttons.Status
import kotlinx.coroutines.flow.update

class GuessingGameViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(GuessingGameUiState())
    val uiState: StateFlow<GuessingGameUiState> = _uiState.asStateFlow()

    private val server = MockServer()

    private lateinit var correctAnswer: String
    private lateinit var answerData: AnswerData

    var userGuess by mutableStateOf("")
        private set

    //Mock functions
    fun getAnswerDetails(){
        //correctAnswer = "Frieren"
        answerData = server.getGameData(1)
        correctAnswer = answerData.name
        _uiState.value = GuessingGameUiState(correctAnswer = correctAnswer, hints = answerData.hints, images = answerData.images)
    }

    //State functions
    fun defaultValues(){

    }

    fun updateGuess(guess: String){
        userGuess = guess
    }

    fun checkUserGuess(){
        val correct = userGuess.trim().equals(correctAnswer, ignoreCase = true)
        val gameOver = _uiState.value.remainingGuesses == 1

        addGuess(correct)

        if (correct){
            enableRemaining()
            _uiState.update {
                it.copy(isCorrect = true, isGameOver = true, remainingGuesses = 0)
            }

        }else{
            val frame = if(!gameOver) 8 - _uiState.value.remainingGuesses else _uiState.value.currentFrame
            updateFrame(frame)
            _uiState.update {
                it.copy(
                    remainingGuesses = it.remainingGuesses.dec(),
                    hintsShown = it.hintsShown.inc(),
                    isGameOver = gameOver,
                    /*currentFrame = frame*/
                )
            }
        }
    }

    //TODO: Add a no redo thing
    private fun addGuess(correct: Boolean){
        val listText = if (correct)
            "✅ $userGuess"
        else if(userGuess.isNotEmpty())
            "❌ $userGuess"
        else
            "❌ Skipped"

        val frameStatus = if (correct) Status.Correct else Status.Wrong
        val newGuesses = _uiState.value.guesses + listText
        _uiState.update { it ->
            it.guessResults[it.currentFrame-1] = frameStatus
            if (it.currentFrame < 6) {
                it.guessResults[it.currentFrame] = Status.NotGuessed
            }
            it.copy(guesses = newGuesses)
        }
    }

    fun updateFrame(frame: Int){
        _uiState.update { it.copy(currentFrame = frame, currentImage = it.images[frame-1]) }
    }

    //TODO: Change so it's proper share functionality and not just copying
    fun shareResults(gameName: String): AnnotatedString {
        var results = "NerdGuesser - $gameName #${_uiState.value.gameNumber}\n🤓 "
        val correctIndex = if (_uiState.value.isCorrect) _uiState.value.guesses.size - 1 else 6
        for (i: Int in 0 until 6){
            results += if (i < correctIndex)
                "🟥 " /*Red square*/
            else if (i == correctIndex)
                "🟩 " /*Green square*/
            else
                "⬛ "
        }
        return AnnotatedString(results)
    }

    private fun enableRemaining(){
        _uiState.update {
            for ((i,status) in it.guessResults.withIndex()){
                if(status == Status.Disabled){
                    it.guessResults[i] = Status.NotGuessed
                }
            }
            it.copy()
        }
    }

    init {
        getAnswerDetails()
    }
}