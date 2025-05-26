package com.example.nerdguesser.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nerdguesser.model.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

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
        viewModelScope.launch {
            Log.d("Anime", "Firebase user before sign up(): ${authRepository.currentUser}")
            updateLoading(true)
            authRepository.signUp(email = email, password = password)
            updateLoading(false)
            Log.d("Anime", "Firebase user after sign up(): ${authRepository.currentUser}")

            //Has the user successfully signed in?
            if (authRepository.currentUser != null){
                showToast("Sign up successful!")
                onSignUpSuccessful()
            }else{
                showToast("Sign up failed!")
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