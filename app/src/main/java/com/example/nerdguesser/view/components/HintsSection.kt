package com.example.nerdguesser.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.example.nerdguesser.model.classes.Hints

@Composable
fun HintsSection(hints: Hints, showHints: Int = 5) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        if (showHints >= 1){
            Text("Hints", fontSize = 18.sp)
            HintText("MyAnimeList rating: " + hints.hint1)
        }
        if (showHints >= 2) HintText("Demographic: " + hints.hint2)
        if (showHints >= 3) HintText("Release date: " + hints.hint3)
        if (showHints >= 4) HintText("Genres: " + hints.hint4)
        if (showHints >= 5) HintText("Studios: " + hints.hint5)
    }
}

@Composable
fun HintText(/*modifier: Modifier = Modifier.padding(10.dp), */text: String){
    Text(/*modifier = Modifier.padding(10.dp),*/ text = text)
}

@Preview
@Composable
fun PreviewHints(){
    val test = Hints()
    Box(Modifier.background(Color.White).fillMaxWidth()){
        HintsSection(test)
    }
}