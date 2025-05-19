package com.example.nerdguesser.viewmodel

import android.util.Patterns
import java.util.regex.Pattern

private const val MIN_PASSWORD_LENGTH = 8
//Does this work?
private const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).*$MIN_PASSWORD_LENGTH,}$"

fun String.isValidEmail(): Boolean{
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean{
    return this.isNotBlank() && Pattern.compile(PASSWORD_PATTERN).matcher(this).matches()
}