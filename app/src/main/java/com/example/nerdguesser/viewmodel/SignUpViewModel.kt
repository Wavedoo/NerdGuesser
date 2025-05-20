package com.example.nerdguesser.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Email
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
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun signUp(email: String, password: String, confirmPassword: String){
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
        /*viewModelScope.launch {
            authRepository.signUp(email = email, password = password, confirmPassword = confirmPassword)
            //Relaunch app?
        }*/
    }

}