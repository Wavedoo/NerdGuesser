package com.example.nerdguesser.view.components.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.nerdguesser.R

//Moved this out of GuessSection.kt

@Composable
fun SubmitButton(guess: String, onSubmit: () -> Unit = {}){
    Button(
        onClick = onSubmit,
        modifier = Modifier.height(40.dp).width(100.dp),
        shape = MaterialTheme.shapes.small
    ) {
        val text: String = if (guess.isNotEmpty())
            stringResource(R.string.submit)
        else
            stringResource(R.string.skip)
        Text(text)
    }
}
