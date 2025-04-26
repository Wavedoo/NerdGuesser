package com.example.nerdguesser.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nerdguesser.R
import com.example.nerdguesser.view.components.NerdGuesserScaffold

@Composable
fun AnimeListScreen(text: String = "No text", onCardClick: (String) -> Unit = {}){
    NerdGuesserScaffold(
        title = "Anime Guesser",
        onBackClick = {}
    ){innerPadding ->
        LazyColumn (modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color.White)){
            items(1){
                GameCard(onCardClick, day = it + 1 )
            }
        }
    }

}

@Composable
fun GameCard(cardClick: (String) -> Unit = {}, day: Int = 1){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        onClick = { cardClick(day.toString()) }
    ) {
        Row(modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(modifier = Modifier.align(Alignment.CenterVertically), text = "Day #$day")
            // Add a vertical space between the author and message texts
            Text(text = "Incomplete")
        }
    }
}
@Preview
@Composable
private fun AnimeListScreenPreview(){
    AnimeListScreen()
}