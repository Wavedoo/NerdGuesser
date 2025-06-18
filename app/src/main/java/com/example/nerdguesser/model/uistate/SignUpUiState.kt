package com.example.nerdguesser.model.uistate

data class SignUpUiState(
    val isLoading: Boolean = false,
    val emailError: Boolean = false,
    val emailErrorMessage: String = "",
    val passwordError: Boolean = false,
    val passwordErrorMessage: String = "",
    val confirmError: Boolean = false,
    val confirmErrorMessage: String = "",
)