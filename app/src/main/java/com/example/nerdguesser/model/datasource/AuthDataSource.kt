package com.example.nerdguesser.model.datasource

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val auth: FirebaseAuth
){
    fun signUp(email: String, password: String, confirmPassword: String){
        //Sign in?
    }
}