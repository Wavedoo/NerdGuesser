package com.example.nerdguesser.viewmodel

data class SignInUiState(
    val isLoading: Boolean = false,
    val emailError: Boolean = false,
    val emailErrorMessage: String = "",
    val passwordError: Boolean = false,
    val passwordErrorMessage: String = "",
)