package com.example.nerdguesser.viewmodel

data class SignUpUiState(
    val emailError: Boolean = false,
    val emailErrorMessage: String = "",
    val passwordError: Boolean = false,
    val passwordErrorMessage: String = "",
    val confirmError: Boolean = false,
    val confirmErrorMessage: String = "",
)