package com.example.nerdguesser.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.example.nerdguesser.R
import com.example.nerdguesser.model.classes.Hints

@Composable
fun HintsSection(hints: List<String>, showHints: Int = 0) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        if (showHints >= 1){
            Text(stringResource(R.string.hints), style = MaterialTheme.typography.titleLarge)
            HintText(stringResource(R.string.hint_rating, hints[0]))
        }
        if (showHints >= 2) HintText(stringResource(R.string.hint_demographic, hints[1]))
        if (showHints >= 3) HintText(stringResource(R.string.hint_release_date, hints[2]))
        if (showHints >= 4) HintText(stringResource(R.string.genres, hints[3]))
        if (showHints >= 5) HintText(stringResource(R.string.studios, hints[4]))
    }
}

@Composable
fun HintText(text: String){
    Text(text = text, style = MaterialTheme.typography.bodyMedium)
}

@Preview
@Composable
fun PreviewHints(){
    val test = listOf("hi", "there", "how", "are", "you?")
    Box(Modifier
        .background(Color.White)
        .fillMaxWidth()){
        HintsSection(test, 5)
    }
}