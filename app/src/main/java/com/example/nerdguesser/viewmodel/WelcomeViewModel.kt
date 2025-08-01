package com.example.nerdguesser.viewmodel

import com.example.nerdguesser.model.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val authRepository: AuthRepository
): BaseViewModel() {
    private val _isSignedIn = MutableStateFlow(isSignedIn())
    val signedIn = _isSignedIn.asStateFlow()

    private fun isSignedIn(): Boolean{
        return authRepository.currentUser != null
    }

    fun createGuestAccount(){
        launchCatching {
            authRepository.createGuestAccount()
            _isSignedIn.value = isSignedIn()
        }
    }
}