package com.example.nerdguesser.model.repository

import com.example.nerdguesser.model.datasource.AuthDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authDataSource: AuthDataSource
){
    fun signUp(email: String, password: String, confirmPassword: String){
        authDataSource.signUp(email = email, password = password, confirmPassword = confirmPassword)
    }
}