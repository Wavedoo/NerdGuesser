package com.example.nerdguesser.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.ViewModel
import com.example.nerdguesser.model.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    fun signUp(email: Email, password: String, confirmPassword: String){

    }

    fun validatePassword(password: String): Boolean{
        return password.length >= 8
    }
}