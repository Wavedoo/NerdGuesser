package com.example.nerdguesser.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nerdguesser.R


//TODO: Figure out if List<String> is the best
@Composable
fun GuessesSection(guesses: List<String>){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.guesses), style = MaterialTheme.typography.titleLarge)
        for (guess in guesses){
            Text(guess, style = MaterialTheme.typography.bodyMedium)
        }

    }
}

@Preview
@Composable
fun PreviewGuessesSection(){
    val guesses: List<String> = listOf("Skipped", "Skipped", "Skipped", "Frieren")
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)) {
        GuessesSection(guesses)
    }
}