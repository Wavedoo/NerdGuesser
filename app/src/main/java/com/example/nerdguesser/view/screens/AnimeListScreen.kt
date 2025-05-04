package com.example.nerdguesser.view.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nerdguesser.R
import com.example.nerdguesser.view.components.LoadingIndicator
import com.example.nerdguesser.view.components.NerdGuesserScaffold
import com.example.nerdguesser.viewmodel.AnimeListViewModel

//Hex is ARGB values
enum class Status(val rgb: Long){
    NotGuessed(0xFFE6E1E8),
    Wrong(0xFFDC3D3D),
    Correct(0xFF3DDC85),
    Close(0xFFCFCC53),
    Disabled(0xFFD9D9D9)
}

/*
TODO: Rewrite firestore to be like:
AnimeFrameDays:
    array 1:
        day 1
        id
    array 2
        day 2
        id
So it's one read with 100 fields instead of
100 reads with 17 fields

In the long run the cost of 100000 people using it all on day 1000 once would be:
Type A:
100,000 people reading two documents (all documents then today's document)
200000 documents - $0

Type B:
100,000 people reading 100 documents once
10000000 documents - $5.91

Lowers the cost of document reads

Also that's 15 fields / document i won't be reading, and +15 for each one i want to play
 */

@Composable
fun AnimeListScreen(onCardClick: (String) -> Unit = {}, animeListViewModel: AnimeListViewModel = hiltViewModel()){
    val gamesList by animeListViewModel.gamesList.collectAsState()

    NerdGuesserScaffold(
        title = stringResource(R.string.anime_guesser),
        onBackClick = {}
    ){innerPadding ->
        if(gamesList.isEmpty()){
            LoadingIndicator(innerPadding)
        }else{
            AnimeListScreenContent(innerPadding, gamesList, onCardClick)
        }
    }

}

@Composable
fun AnimeListScreenContent(
    innerPadding: PaddingValues,
    gamesList: List<String>,
    onCardClick: (String) -> Unit = {}
){
    LazyColumn (modifier = Modifier
        .padding(innerPadding)
        .fillMaxSize()
    ){
        //val test: List<GameData> = emptyList()

        itemsIndexed(gamesList){index, id ->
            Log.d("Anime", "Day: $index, ID: $id")
            GameCard(onCardClick, id = id, day = index + 1)

        }
        /*items(2){
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
        }*/
    }
}

@Composable
fun GameCard(
    onCardClick: (String) -> Unit = {},
    id: String,
    day: Int = 1,
    status: Status = Status.NotGuessed
    //gameId: String = ""
){
    //Temp:
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(status.rgb)),
        onClick = {
            Log.d("Anime", "From card: $id")
            //GameDataSource.id = id
            onCardClick(id)
        }
    ) {
        Row(modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(modifier = Modifier.align(Alignment.CenterVertically), text = "Day #${day}")
            Text(text = "Incomplete")
        }
    }
}
@Preview
@Composable
private fun AnimeListScreenPreview(){
    AnimeListScreen()
}