package com.example.nerdguesser

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nerdguesser.utils.extensions.isValidEmail
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

//Current format: classOrFileName_InputType_ReturnValue
//Or whatever makes sense
@RunWith(AndroidJUnit4::class)
class EmailValidatorTest {
    @Test
    fun emailValidator_CorrectEmail_ReturnsTrue(){
        assertTrue("anime@nerdguesser.ca".isValidEmail())
    }

    @Test
    fun emailValidator_BlankEmail_ReturnsFalse(){
        assertFalse("".isValidEmail())
    }
}