package com.example.nerdguesser.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nerdguesser.model.repository.AuthRepository
import com.example.nerdguesser.model.uistate.SignInUiState
import com.example.nerdguesser.model.uistate.SignUpUiState
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWebException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
): MainViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()
    private val _isSignedIn = MutableStateFlow(isSignedIn())
    val signedIn = _isSignedIn.asStateFlow()

    //val userState: Flow<FirebaseUser?> = authRepository.currentUser

/*    init {
        //Testing purposes
        authRepository.signOut()
    }*/

    fun signUp(
        email: String,
        password: String,
        confirmPassword: String,
        onSignUpSuccessful: () -> Unit,
        showToast: (String) -> Unit
    ){
        resetErrors()
        var valid = true
        if(!email.isValidEmail()){
            Log.d("Anime","Invalid email")
            _uiState.update {
                it.copy(emailError = true, emailErrorMessage = "Invalid email")
            }
            valid = false
        }

        if(!password.isValidPassword()){
            Log.d("Anime", "Invalid password")
            _uiState.update {
                it.copy(passwordError = true, passwordErrorMessage = "Password does not meet the requirements")
            }
            valid = false
        }

        if(!password.equals(confirmPassword, ignoreCase = false)){
            Log.d("Anime", "Passwords do not match")
            _uiState.update {
                it.copy(confirmError = true, confirmErrorMessage = "Passwords do not match")
            }
            valid= false
        }

        if(!valid){
            return
        }

        launchCatching (showAuthErrorState = {displayAuthError(it)}) {
            Log.d("Anime", "Firebase user before sign up(): ${authRepository.currentUser}")
            updateLoading(true)
            authRepository.signUp(email = email, password = password)
            updateLoading(false)
            Log.d("Anime", "Firebase user after sign up(): ${authRepository.currentUser}")

            _isSignedIn.value = true
        }
    }

    private fun updateLoading(isLoading: Boolean) {
        _uiState.update {
            it.copy(isLoading = isLoading)
        }
    }
    private fun resetErrors(){
        _uiState.update{
            SignUpUiState()
        }
    }

    private fun displayAuthError(throwable: Throwable){
        when(throwable){
            is FirebaseAuthWebException -> _uiState.update {
                it.copy(
                    passwordError = true,
                    passwordErrorMessage = "The password is not strong enough",
                    confirmError = true,
                    confirmErrorMessage = "The password is not strong enough"
                )
            }
            is FirebaseAuthInvalidCredentialsException -> _uiState.update {
                it.copy(emailError = true, emailErrorMessage = "The email address is malformed")
            }
            is FirebaseAuthUserCollisionException -> _uiState.update {
                it.copy(emailError = true, emailErrorMessage = "The email address is already in use")
            }
        }
    }
    private fun isSignedIn(): Boolean{
        return authRepository.currentUser != null
    }
}