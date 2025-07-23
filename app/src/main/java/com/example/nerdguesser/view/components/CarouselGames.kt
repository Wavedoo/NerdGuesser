package com.example.nerdguesser.view.components

import android.util.Log
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import com.example.nerdguesser.model.classes.CarouselItem
import com.example.nerdguesser.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.example.nerdguesser.utils.extensions.getCurrentSelectedItem
import com.example.nerdguesser.utils.extensions.selectedItemIndex


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarouselGames(){
    val games = listOf(
        CarouselItem(
            1,
            R.drawable.guess_anime_pic,
            "Guess The Anime",
            "Guess the daily anime or play previous games!",
            contentDescription = "Anime characters I guess",
        ),
        CarouselItem(2,
            R.drawable.frieren_landscape_6,
            "Frieren Frenzy",
            "Play this daily Frieren related quiz?",
            contentDescription = "Frieren",
        ),
        CarouselItem(
            3,
            R.drawable.guess_anime_pic,
            "Anime the Guess",
            "Anime the daily guess or play previous games!",
            contentDescription = "Anime characters I guess again",
        ),
        CarouselItem(
            4,
            R.drawable.guess_anime_pic,
            "",
            "",
            contentDescription = "",
        )
    )
    //Makes sure to start on the first in the list.
    var index = Int.MAX_VALUE / 2
    index -= index % games.size
    val lazyListState = rememberLazyListState(index)

    val game by remember {
        derivedStateOf {
            Log.d("Anime", "Selected item index updated: ${lazyListState.selectedItemIndex}")
            games[lazyListState.selectedItemIndex % games.size]
        }
    }

    val game2 = games[lazyListState.selectedItemIndex % games.size]

    Text("${lazyListState.selectedItemIndex % games.size + 1} / ${games.size}")

    GameWheel(
        lazyListState = lazyListState,
        games = games
    )

/*    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = lazyListState,
        flingBehavior = snapBehavior
    ) {
        items(Int.MAX_VALUE) { index ->
            val game = games[index % games.size]
            CarouselImage(game.imageId, game.gameTitle, game.contentDescription)
        }
    }*/
    Text(game.gameTitle)
    Text(game.gameDescription)
}