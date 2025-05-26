package com.example.nerdguesser.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nerdguesser.model.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel(){
    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    //Should this be called attemptSignIn?
    fun signIn(email: String, password: String, onSignInSuccessful: () -> Unit){
        _uiState.update{
            SignInUiState()
        }
        viewModelScope.launch {
            Log.d("Anime", "Attempting to sign in.")
            updateLoading(true)
            authRepository.signIn(email, password)
            updateLoading(false)
            Log.d("Anime", "Sign in attempt over.")

            if (authRepository.currentUser != null){
                onSignInSuccessful()
            }else{
                _uiState.update {
                    it.copy(
                        emailError = true,
                        emailErrorMessage = "The username or password you entered is incorrect",
                        passwordError = true,
                        passwordErrorMessage = "The username or password you entered is incorrect"
                    )
                }
            }
        }


    }

    private fun updateLoading(isLoading: Boolean) {
        _uiState.update {
            it.copy(isLoading = isLoading)
        }
    }
    fun isSignedIn(): Boolean{
        return authRepository.currentUser != null
    }
}