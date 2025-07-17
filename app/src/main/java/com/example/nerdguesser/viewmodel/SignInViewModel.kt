package com.example.nerdguesser.viewmodel

import android.util.Log
import com.example.nerdguesser.model.repository.AuthRepository
import com.example.nerdguesser.model.uistate.SignInUiState
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
): BaseViewModel(){
    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()
    private val _isSignedIn = MutableStateFlow(isSignedIn())
    val signedIn = _isSignedIn.asStateFlow()

    init {
        Log.d("Anime", "Is signed in is: ${_isSignedIn.value}")
    }
    //Should this be called attemptSignIn?
    fun signIn(email: String, password: String, onSignInSuccessful: () -> Unit){
        resetErrors()

        if(!validTextfields(email, password)){
            return
        }

        launchCatching(updateUiState = ::uiStateError) {
            //call sign in
            Log.d("Anime", "Attempting to sign in.")
            updateLoading(true)
            authRepository.signIn(email, password)
            updateLoading(false)
            Log.d("Anime", "Sign in attempt over.")
            //Call toast on successful maybe maybe not
            //Sign in worked.
            _isSignedIn.value = true
        }

        //if not signed in then error
/*        viewModelScope.launch {
            Log.d("Anime", "Attempting to sign in.")
            updateLoading(true)
            authRepository.signIn(email, password)
            updateLoading(false)
            Log.d("Anime", "Sign in attempt over.")

            if (authRepository.currentUser != null){
                onSignInSuccessful()
            }else{
                credentialsError()
            }
        }*/


    }

    private fun updateLoading(isLoading: Boolean) {
        _uiState.update {
            it.copy(isLoading = isLoading)
        }
    }
    fun isSignedIn(): Boolean{
        return authRepository.currentUser != null
    }

    private fun uiStateError(throwable: Throwable){
        if(throwable is FirebaseAuthException){
            credentialsError()
        }

    }
    private fun credentialsError(){
        Log.d("Anime", "Sign in failed running credentialsError()")
        _uiState.update {
            it.copy(
                emailError = true,
                emailErrorMessage = "The username or password you entered is incorrect",
                passwordError = true,
                passwordErrorMessage = "The username or password you entered is incorrect",
                snackbarError = "Sign in failed, please make sure credentials are correct."
            )
        }
    }

    private fun snackBar(){

    }

    private fun validTextfields(email: String, password: String): Boolean{
        var valid = true
        if(email.isEmpty()){
            _uiState.update {
                it.copy(emailError = true, emailErrorMessage = "Enter your email")
            }
            valid = false
        }
        if(password.isEmpty()){
            _uiState.update {
                it.copy(passwordError = true, passwordErrorMessage = "Enter your password")
            }
            valid = false
        }
        return valid
    }
    private fun resetErrors(){
        _uiState.update{
            SignInUiState()
        }
    }

    fun snackbarErrorShown(){
        _uiState.update {
            it.copy(snackbarError = null)
        }
    }
}