package com.example.nerdguesser.viewmodel

import android.util.Patterns
import java.util.regex.Pattern

private const val MIN_PASSWORD_LENGTH = 6
//Does this work?
// Stolen from makeitso project
private const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"
private const val STRONG_PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$"
//Testing purposes cause i'm lazy
private const val WEAK_PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=\\S+$).{$MIN_PASSWORD_LENGTH,}$"

fun String.isValidEmail(): Boolean{
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean{
    return this.isNotBlank() && Pattern.compile(WEAK_PASSWORD_PATTERN).matcher(this).matches()
}