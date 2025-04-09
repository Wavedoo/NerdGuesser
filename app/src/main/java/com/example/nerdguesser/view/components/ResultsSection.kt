package com.example.nerdguesser.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nerdguesser.view.components.buttons.GuessesButton
import com.example.nerdguesser.view.components.buttons.ShareButton

@Composable
fun ResultsSection(
    correct: Boolean = true,
    answer: String = "Frieren: Beyond Journey's End",
    showGuesses: Boolean = false,
    onGuessesClick: () -> Unit = {},
    onShareClick: () -> Unit = {}
){
    val resultColour: Color = if (correct) Color(0xFF3DDC85) else Color(0xFFDC3D3D)
    val resultMessage: String = if (correct) "You did it!" else "Womp womp"
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        /*verticalArrangement = Arrangement.spacedBy(10.dp)*/
    ) {
        Text(text = "The answer is:", fontSize = 28.sp)
        Text(text = answer, color = resultColour, fontSize = 28.sp)
        Text(text = resultMessage, color = resultColour, fontSize = 28.sp, modifier = Modifier.padding(vertical = 10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            GuessesButton(showGuesses = showGuesses, onClick = onGuessesClick)
            ShareButton(onShareClick = onShareClick)
        }
        //TODO: add onClick = {showGuesses = !showGuesses}
        //TODO: Continue reading this https://developer.android.com/develop/ui/compose/state-hoisting#business-logic
    }
}

@Preview
@Composable
fun PreviewSection(){
    Column(modifier = Modifier.fillMaxWidth().background(Color.White)) {
        ResultsSection()
    }
}