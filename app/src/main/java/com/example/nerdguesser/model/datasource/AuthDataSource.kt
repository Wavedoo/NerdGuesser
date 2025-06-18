package com.example.nerdguesser.model.datasource

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

//I know these should be renamed to remote data source but i'm lazy.
class AuthDataSource @Inject constructor(
    private val auth: FirebaseAuth
){
    /*
    TODO: Find out if there's a reason makeitso does it like this:

       val currentUser: FirebaseUser? get() = auth.currentUser
        val currentUserIdFlow: Flow<String?>
            get() = callbackFlow {
                val listener = FirebaseAuth.AuthStateListener { _ -> this.trySend(currentUser?.uid) }
                auth.addAuthStateListener(listener)
                awaitClose { auth.removeAuthStateListener(listener) }
            }
     */

    //val currentUser: FirebaseUser? get() = auth.currentUser
    //Change to stateflow?
    //I simply won't use this until I find a reason to collect the user as a flow.
    //I think I found a reason

    val currentUser: FirebaseUser? get() = auth.currentUser

    //current user should be from an authstatelistener because what if a user gets signed out while still in the app right?
    //The question is should this be Flow<String> for the ID?

    //Should this be statein?
    //Maybe implement this later
    val currentUserFlow: Flow<FirebaseUser?>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { this.trySend(auth.currentUser) }
            auth.addAuthStateListener(listener)
            awaitClose {auth.removeAuthStateListener(listener) }
        }


    suspend fun signUp(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun signIn(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password).await()
    }

    fun signOut(){
        auth.signOut()
    }

    fun getUser(): FirebaseUser?{
        return auth.currentUser
    }
}