package com.example.nerdguesser.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nerdguesser.model.classes.Hints

@Composable
fun GameOverSection(
    correct: Boolean = false,
    answer: String = "Frieren: Beyond Journey's End",
    guesses: List<String> = listOf(),
    hints: List<String> = listOf(),
    onShareClick: () -> Unit = {}
){
    var showGuesses by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ResultsSection(
            correct = correct,
            answer = answer,
            showGuesses = showGuesses,
            onGuessesClick = {showGuesses = !showGuesses},
            onShareClick = onShareClick
        )
        if(showGuesses){
            GuessesSection(guesses)
        }else{
            HintsSection(hints, 5)
        }
    }
}

@Preview
@Composable
fun PreviewGameOver(){
    Column(modifier = Modifier.fillMaxWidth().background(color = Color.White)){
        GameOverSection()
    }
}

