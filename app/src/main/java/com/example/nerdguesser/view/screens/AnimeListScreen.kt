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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.nerdguesser.R
import com.example.nerdguesser.view.components.NerdGuesserScaffold
import com.example.nerdguesser.view.components.NerdLoadingIndicator
import com.example.nerdguesser.view.navigation.ScreenRoute
import com.example.nerdguesser.viewmodel.AnimeListViewModel

//Hex is ARGB values
enum class Status(val rgb: Long){
    NotGuessed(0xFFE6E1E8),
    Wrong(0xFFDC3D3D),
    Correct(0xFF3DDC85),
    Close(0xFFCFCC53),
    Disabled(0xFFD9D9D9)
}

@Composable
fun AnimeListScreen(
    navController: NavController,
    animeListViewModel: AnimeListViewModel = hiltViewModel()
){
    val games by animeListViewModel.games.collectAsStateWithLifecycle()
    Log.d("Anime", "AnimeListScreen called")

    NerdGuesserScaffold(
        title = stringResource(R.string.anime_guesser),
        onBackClick = {}
    ){innerPadding ->
        if(games < 0){
            NerdLoadingIndicator(innerPadding)
        }else{
            AnimeListScreenContent(innerPadding, games){
                navController.navigate(ScreenRoute.AnimeGuesserGameRoute(day = it))
            }
        }
    }

}

@Composable
fun AnimeListScreenContent(
    innerPadding: PaddingValues,
    gameCount: Int,
    onCardClick: (Int) -> Unit = {}
){
    Log.d("Anime", "AnimeListScreenContent called")
    LazyColumn (modifier = Modifier
        .padding(innerPadding)
        .fillMaxSize()
    ){
        items(gameCount){day ->
            Log.d("Anime", "Day: ${day+1}")
            GameCard(onCardClick, day = day + 1)
        }
    }
}

@Composable
fun GameCard(
    onCardClick: (Int) -> Unit = {},
    day: Int = 1,
    status: Status = Status.NotGuessed
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(status.rgb)),
        onClick = {
            Log.d("Anime", "From card: $day")
            onCardClick(day)
        }
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
    //AnimeListScreen()
}