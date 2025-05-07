package com.example.nerdguesser.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nerdguesser.model.repository.GameDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//I could possibly implement a mainviewmodel with a coroutine function like in: https://github.com/FirebaseExtended/make-it-so-android/blob/5c3b28c04f7582c7e60b30f3693d770b46819646/v2/app/src/main/java/com/google/firebase/example/makeitso/MainViewModel.kt
@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val gameDataRepository: GameDataRepository
) : ViewModel() {
    private val _gamesList = MutableStateFlow<List<String>>(emptyList())
    val gamesList: StateFlow<List<String>> = _gamesList.asStateFlow()

    init {
        viewModelScope.launch {
            _gamesList.value = gameDataRepository.getGamesList()
        }
    }

    /*private var firestore: FirebaseFirestore
    //private lateinit var gamesRef: CollectionReference

    //private val firestore = FirestoreTest()
    //val itemList: MutableState<List<Content>> = mutableStateOf(emptyList())
    //val gamesList: SnapshotStateList<GameData> = mutableStateListOf<GameData>()
    //var gamesList = MutableStateFlow<List<String>>(emptyList())
    private val _gamesList = MutableStateFlow<List<String>>(emptyList())
    val gamesList: StateFlow<List<String>> = _gamesList.asStateFlow()

    //var gamesList by mutableStateOf("")
    //    private set

    private fun updateGameList() {
        val service = FirestoreService()
        service.getGamesList { list ->
            _gamesList.value = list.toMutableStateList()
            Log.d("Anime", "Games list test: $gamesList")
        }
        Log.d("Anime", "gamesList: $gamesList")
*//*        firestore.collection("AnimeFrameGuesser")
            .orderBy("day")
            .get()
            .addOnSuccessListener {  result ->
                for (document in result){
                    Log.d("anime", "ID ${document.id}")
                    Log.d("anime", document.data.toString())
                    gamesList.add(GameDataUtil.documentToGameData(document))
                }

            }
            .addOnFailureListener { exception ->
                Log.d("anime", "Error getting documents: ", exception)
            }*//*
    }
    init {
        firestore = Firebase.firestore
        Log.d("anime", _gamesList.value.size.toString())
        updateGameList()
    }



    fun startFirestore(){
        firestore = Firebase.firestore
        //gamesRef = firestore.collection("AnimeFrameGuesser")
    }
    fun callGames(){
        firestore.collection("cities")
            .get()
            .addOnSuccessListener { result ->

            }
            .addOnFailureListener { exception ->
                //Log.d(TAG, "Error getting documents: ", exception)
            }
    }*/
}