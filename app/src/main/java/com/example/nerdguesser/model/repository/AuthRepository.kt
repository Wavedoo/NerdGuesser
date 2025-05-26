package com.example.nerdguesser.model.repository

import com.example.nerdguesser.model.datasource.AuthDataSource
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authDataSource: AuthDataSource
){
    //val currentUser: FirebaseUser? = authDataSource.currentUser
    //val currentUser: Flow<FirebaseUser?> = authDataSource.currentUser
    val currentUser get() = authDataSource.currentUser
    suspend fun signUp(email: String, password: String){
        authDataSource.signUp(email = email, password = password)
    }

    suspend fun signIn(email: String, password: String){
        authDataSource.signIn(email, password)
    }
    fun signOut(){
        authDataSource.signOut()
    }

    fun getUser(): FirebaseUser? {
        return authDataSource.getUser()
    }
}