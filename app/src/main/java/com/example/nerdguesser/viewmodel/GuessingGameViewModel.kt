package com.example.nerdguesser.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.example.nerdguesser.model.classes.GameData
import com.example.nerdguesser.model.repository.GameDataRepository
import com.example.nerdguesser.model.repository.ImageDataRepository
import com.example.nerdguesser.view.components.buttons.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

val options = listOf(
    "Naruto",
    "Naruto Shippuden",
    "Frieren",
    "Spy x Family",
    "Neon Genesis Evangelion",
    "86",
    "Rock Is a Lady's Modesty",
    "Attack on Titan",
    "Shingeki no Kyojin",
    "Beastars",
    "Dandadan",
    "Danganronpa",
    "Danganronpa: The Animation"
)


@HiltViewModel
class GuessingGameViewModel @Inject constructor(
    private val gameDataRepository: GameDataRepository,
    private val imageDataRepository: ImageDataRepository
) : ViewModel() {

    //TODO: Read this https://developer.android.com/develop/ui/compose/state-saving
    private val _uiState = MutableStateFlow(GuessingGameUiState())
    val uiState: StateFlow<GuessingGameUiState> = _uiState.asStateFlow()

    private lateinit var gameData: GameData
    private lateinit var correctAnswer: String

    var userGuess by mutableStateOf(TextFieldValue())
        private set
    /*var userGuess by mutableStateOf("")
        private set*/

    private var images: MutableList<ImageBitmap> = mutableStateListOf()

    //TODO: Change so ID comes from datasource
    //I'll probably leave it. it should be fine...
    fun tempInit(id: String){
        Log.d("Anime", "tempInit repeated call check")
        viewModelScope.launch {
            gameData = gameDataRepository.getGameData(id)
            Log.d("Anime", "viewModelScope.launch repeated call check")
            createState()

        }
    }

    private suspend fun createState(){
        correctAnswer = gameData.name
        images = imageDataRepository.getImages(gameData.imageFolder).toMutableStateList()
        _uiState.value = GuessingGameUiState(gameData = gameData, images = images)
    }

    fun updateGuess(guess: TextFieldValue){
        userGuess = guess
        filterResults()
    }

    fun checkUserGuess(){
        val correct = userGuess.text.trim().equals(correctAnswer, ignoreCase = true)
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
                    maxFrame = it.maxFrame.inc()
                )
            }
        }
        resetGuess()
    }

    //TODO: Add a no redo thing
    private fun addGuess(correct: Boolean){
        val listText = if (correct)
            "✅ ${userGuess.text}"
        else if(userGuess.text.isNotEmpty())
            "❌ ${userGuess.text}"
        else
            "❌ Skipped"

        val frameStatus = if (correct) Status.Correct else Status.Wrong
        val newGuesses = _uiState.value.guesses + listText

        _uiState.update {
            it.guessResults[it.maxFrame-1] = frameStatus
            if (it.currentFrame < 6) {
                it.guessResults[it.maxFrame] = Status.NotGuessed
            }
            it.copy(guesses = newGuesses)
        }
    }

    //TODO: Check if I even need this
    fun updateFrame(frame: Int){
        _uiState.update { it.copy(currentFrame = frame) }
    }

    //TODO: Change so it's proper share functionality and not just copying
    fun shareResults(gameName: String): AnnotatedString {
        var results = "NerdGuesser - $gameName #${_uiState.value.gameData.day}\n🤓 "
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

    private fun filterResults(){
        val word = userGuess.text

        if(word.isEmpty()){
            //I don't want a list to show when the guess is empty
            _uiState.update { it.copy(filteredResults = emptyList()) }
        }else{
            //starts the list with words that start with the word
            val filteredList: MutableList<String> = options.filter { it.startsWith(word, ignoreCase = true) }.toMutableList()
            //adds to the list words that contain the word but don't start with the word
            filteredList.addAll(options.filter { !it.startsWith(word, ignoreCase = true) && it.contains(word, ignoreCase = true) })

            _uiState.update { it.copy(filteredResults = filteredList) }
        }
    }

    private fun resetGuess(){
        userGuess = TextFieldValue()
        _uiState.update { it.copy(filteredResults = emptyList()) }
    }
}