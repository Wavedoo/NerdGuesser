package com.example.nerdguesser.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nerdguesser.model.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel(){

    //Should this be called attemptSignIn?
    fun signIn(email: String, password: String){

    }
}