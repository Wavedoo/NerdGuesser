package com.example.nerdguesser

import com.example.nerdguesser.viewmodel.isValidPassword
import org.junit.Assert.assertFalse
import org.junit.Test
import org.junit.Assert.assertTrue

//Current format: classOrFileName_InputType_ReturnValue
//Or whatever makes sense
class PasswordValidatorTest {
    @Test
    fun passwordValidator_ValidPassword_ReturnsTrue(){
        assertTrue("Anime!".isValidPassword())
        assertTrue("password".isValidPassword())
        assertTrue("CODING".isValidPassword())
        assertTrue("coding".isValidPassword())
    }

    @Test
    fun emailValidator_BlankPassword_ReturnsFalse(){
        assertFalse("".isValidPassword())
    }

    @Test
    fun emailValidator_ShortPassword_ReturnsFalse(){
        assertFalse("Anime".isValidPassword())
    }
}