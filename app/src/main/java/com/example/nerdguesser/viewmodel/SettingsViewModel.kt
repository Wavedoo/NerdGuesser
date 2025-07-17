package com.example.nerdguesser.viewmodel

import com.example.nerdguesser.model.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val authRepository: AuthRepository
): BaseViewModel(){
    private val _isSignedIn = MutableStateFlow(true)
    val signedIn = _isSignedIn.asStateFlow()

    fun signOut(){
        authRepository.signOut()
        _isSignedIn.value = false
    }
}