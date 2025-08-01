package com.example.nerdguesser.viewmodel

import com.example.nerdguesser.model.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val authRepository: AuthRepository
): BaseViewModel() {


    fun createGuestAccount(){
        launchCatching {
            authRepository.createGuestAccount()
        }
    }
}