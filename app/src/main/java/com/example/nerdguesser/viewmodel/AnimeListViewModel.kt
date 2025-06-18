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
) : MainViewModel() {
    private val _gamesList = MutableStateFlow<List<String>>(emptyList())
    val gamesList: StateFlow<List<String>> = _gamesList.asStateFlow()

    init {
        viewModelScope.launch {
            _gamesList.value = gameDataRepository.getGamesList()
        }
    }
}