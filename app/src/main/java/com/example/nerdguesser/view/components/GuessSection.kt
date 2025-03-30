package com.example.nerdguesser.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.nerdguesser.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun GuessSection(){
    var guess by remember { mutableStateOf("") }

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = guess,
            onValueChange = { guess = it },
            label = null,
            placeholder = { Text(stringResource(R.string.guess_the_anime)) },
            modifier =  Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Text("6 guesses remaining!", Modifier.padding(vertical = 10.dp))
        SubmitButton(guess)

    }
}

//TODO: Possibly move out of file
@Composable
fun SubmitButton(guess: String){
    Button(
        onClick = {},
        modifier = Modifier
            .height(40.dp)
            .width(100.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        val text: String = if (guess.isNotEmpty())
            stringResource(R.string.submit)
        else
            stringResource(R.string.skip)
        Text(text)
    }
}


@Preview
@Composable
fun PreviewGuess(){
    Column(Modifier.background(Color.White)) {
        GuessSection()

    }
}