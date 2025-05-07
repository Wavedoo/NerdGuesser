package com.example.nerdguesser.viewmodel

import android.graphics.BitmapFactory
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.viewModelScope
import com.example.nerdguesser.model.classes.AnswerData
import com.example.nerdguesser.model.classes.GameData
import com.example.nerdguesser.model.repository.GameDataRepository
import com.example.nerdguesser.model.repository.ImageDataRepository
import com.example.nerdguesser.model.utils.GameDataUtil
import com.example.nerdguesser.view.components.buttons.Status
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.component1
import com.google.firebase.storage.component2
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO: Switch to hilt
@HiltViewModel
class GuessingGameViewModel @Inject constructor(
    private val gameDataRepository: GameDataRepository,
    private val imageDataRepository: ImageDataRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewGuessingGameUiState())
    val uiState: StateFlow<NewGuessingGameUiState> = _uiState.asStateFlow()

    private lateinit var gameData: GameData

    //private lateinit var gameData: GameData
/*    private val _gameData = MutableStateFlow<GameData>(GameDataUtil.test)
    val gameData: StateFlow<GameData>
        get() = _gameData.asStateFlow()*/
    private lateinit var correctAnswer: String
    private lateinit var answerData: AnswerData

    var userGuess by mutableStateOf("")
        private set

    val FIVE_MEGABYTES: Long = 5 * 1024 * 1024
    var images: MutableList<ImageBitmap> = mutableStateListOf()


    //TODO: Change so ID comes from datasource
    fun tempInit(id: String){
        Log.d("Anime", "tempInit repeated call check")
        viewModelScope.launch {
            gameData = gameDataRepository.getGameData(id)
            //gameData = GameDataUtil.test
            Log.d("Anime", "viewModelScope.launch repeated call check")
            createState()
        }

    }

    //Temporary
    suspend fun createState(){
        correctAnswer = gameData.name
        //tryFindingFile()
        images = imageDataRepository.getImages(gameData.imageFolder).toMutableStateList()
        Log.d("Anime", "createState repeated call check")
        _uiState.value = NewGuessingGameUiState(gameData = gameData, images = images)
    }

    //TODO: fix so the images are in order
    /*fun getImages(){
        val storage = Firebase.storage

        var gsReference = storage.getReferenceFromUrl(gameData.imageFolder);
        //val listRef = storage.reference.child("")
        Log.d("Anime", "gsReference: $gsReference")

        gsReference.listAll()
            .addOnSuccessListener { (items, prefixes) ->
                for (item in items) {
                    Log.d("Anime", "item: ${item}")
                    addImage(item)
                }
                Log.d("Anime", "Adding complete: ${_uiState.value.images.size}")
            }
            .addOnFailureListener {
                Log.d("Anime", "Failure")
            }
        Log.d("Anime", "Images: $images")
    }*/

    fun addImage(path: StorageReference){
        Log.d("Anime", "addImage")
        path.getBytes(FIVE_MEGABYTES).addOnSuccessListener {
            image ->
            Log.d("Anime", "onSuccess")
            val bitmap: ImageBitmap = BitmapFactory.decodeByteArray(image, 0, image.size).asImageBitmap()
            images.add(bitmap)
            _uiState.update {
                it.copy(images = images)
            }
            Log.d("Anime", "New size: " + images.size.toString())
        }
        Log.d("Anime", "Size: " + images.size.toString())
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
                    maxFrame = it.maxFrame.inc()
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
            val maxFrame = it.remainingGuesses
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

/*    private fun test(){
        println("Data test: ${dataTest.id}")
    }*/

    fun startGame(id: String){
        //awaitGameDataTest(id)
/*        firestoreService.getGameData(id) {
            val temp = it
            Log.d("Anime", "Game data retrieved $temp")
        }*/
    }



/*    fun awaitGameDataTest(id: String) = runBlocking{
        launch {
            _gameData.value = firestoreService.awaitGameData(id = id)
            createState()

        }
    }*/
    /*fun startGame(game: GameData){
        gameData = game
        Log.d("Anime", "Game ID is: ${game.id}")
        getAnswerDetails()
    }*/
    init {
        //correctAnswer = "test"
        //gameData = GameDataSource.
        //gameData = GameDataUtil.test
        //createState()
        //getAnswerDetails()
    }
}