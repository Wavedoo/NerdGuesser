package com.example.nerdguesser.viewmodel

import com.example.nerdguesser.model.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserInfoTestViewModel @Inject constructor(
    private val authRepository: AuthRepository
): BaseViewModel() {
    fun getUser(): FirebaseUser?{
        return authRepository.getUser()
    }

    fun signOut(){
        authRepository.signOut()
    }
}