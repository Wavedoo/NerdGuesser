package com.example.nerdguesser.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nerdguesser.R
import com.example.nerdguesser.view.components.NerdGuesserScaffold

//Hex is ARGB values
enum class Status(val rgb: Long){
    NotGuessed(0xFFE6E1E8),
    Wrong(0xFFDC3D3D),
    Correct(0xFF3DDC85),
    Close(0xFFCFCC53),
    Disabled(0xFFD9D9D9)
}

@Composable
fun AnimeListScreen(onCardClick: (String) -> Unit = {}){
    NerdGuesserScaffold(
        title = stringResource(R.string.anime_guesser),
        onBackClick = {}
    ){innerPadding ->
        LazyColumn (modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
        ){
            items(2){
                GameCard(onCardClick, day = it + 1, status = Status.NotGuessed )
            }
            item{
                GameCard(onCardClick, day = 3, status = Status.Wrong)
            }
            item{
                GameCard(onCardClick, day = 4, status = Status.Correct)
            }
            item{
                GameCard(onCardClick, day = 5, status = Status.Close)
            }
            item{
                GameCard(onCardClick, day = 6, status = Status.Disabled)
            }
            item{
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    onClick = { onCardClick("f") }
                ) {
                    Row(modifier = Modifier
                        .padding(all = 8.dp)
                        .fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text(modifier = Modifier.align(Alignment.CenterVertically), text = "Day #7")
                        Text(text = "Incomplete")
                    }
                }
            }
            item{
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(Status.NotGuessed.rgb)),
                    onClick = { onCardClick("f") }
                ) {
                    Row(modifier = Modifier
                        .padding(all = 8.dp)
                        .fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text(modifier = Modifier.align(Alignment.CenterVertically), text = "Day #8")
                        Text(text = "Incomplete")
                    }
                }
            }
        }
    }

}

@Composable
fun GameCard(
    onCardClick: (String) -> Unit = {},
    day: Int = 1,
    status: Status
    //gameId: String = ""
){
    //Temp:
    val gameId = day.toString()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(status.rgb)),
        onClick = { onCardClick(gameId) }
    ) {
        Row(modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(modifier = Modifier.align(Alignment.CenterVertically), text = "Day #$day")
            Text(text = "Incomplete")
        }
    }
}
@Preview
@Composable
private fun AnimeListScreenPreview(){
    AnimeListScreen()
}