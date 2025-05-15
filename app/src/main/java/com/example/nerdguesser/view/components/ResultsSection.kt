package com.example.nerdguesser.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nerdguesser.R
import com.example.nerdguesser.view.components.buttons.GuessesButton
import com.example.nerdguesser.view.components.buttons.ShareButton

//TODO: Add a bit of motion
@Composable
fun ResultsSection(
    correct: Boolean,
    answer: String,
    showGuesses: Boolean = false,
    onGuessesClick: () -> Unit,
    onShareClick: () -> Unit
){
    val resultColour: Color = if (correct) Color(0xFF3DDC85) else Color(0xFFDC3D3D)
    val resultMessage: String = if (correct)
        stringResource(R.string.result_correct)
    else
        stringResource(R.string.result_wrong)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        /*verticalArrangement = Arrangement.spacedBy(10.dp)*/
    ) {
        //TODO: Find out if annotatedString is better
        ResultsText(text = stringResource(R.string.the_answer_is))
        ResultsText(text = answer, color = resultColour)
        ResultsText(text = resultMessage, color = resultColour, modifier = Modifier.padding(10.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            GuessesButton(showGuesses = showGuesses, onClick = onGuessesClick)
            ShareButton(onShareClick = onShareClick)
        }
    }
}

//TODO: Extend material3 themeing to include correct and incorrect for light and dark themes
@Composable
fun ResultsText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground
){
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
        color = color,
        modifier = modifier
    )
}
@Preview
@Composable
fun PreviewSection(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
    ) {
        ResultsSection(
            correct = true,
            answer = "placeholder",
            onGuessesClick = {},
            onShareClick = {}
        )
    }
}