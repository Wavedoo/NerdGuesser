package com.example.nerdguesser.view.components.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GuessesButton(
    showGuesses: Boolean = false,
    onClick: () -> Unit = {}
){
    val text: String = if(showGuesses)
        "Hide guesses"
    else
        "Show guesses"

    //TODO: Redo button formats and stuff
    Button(
        onClick = {onClick()},
        modifier = Modifier
            .height(40.dp)
            .width(140.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text)
    }
}

@Preview
@Composable
fun preview(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

    }
    GuessesButton()
}