package com.example.nerdguesser.view.components

import android.util.Log
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nerdguesser.model.classes.CarouselItem
import com.example.nerdguesser.utils.extensions.selectedItemIndex
import com.example.nerdguesser.view.components.buttons.NavigationButton

/*
This dosen't need to be a wheel at all,
it might actually be worse.
But I did it because I could.
*/
@Composable
fun GameWheel(
    navController: NavController,
    games: List<CarouselItem>
){
    //Makes sure to start on the first in the list.
    var index = Int.MAX_VALUE / 2
    index -= index % games.size
    val lazyListState = rememberLazyListState(index)
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)


    val currentGame by remember {
        derivedStateOf {
            Log.d("Anime", "Selected item index updated: ${lazyListState.selectedItemIndex}")
            games[lazyListState.selectedItemIndex % games.size]
        }
    }

    Text("${lazyListState.selectedItemIndex % games.size + 1} / ${games.size}")

    //TODO: Worry about centering the first item later.
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = lazyListState,
        flingBehavior = snapBehavior
    ) {
        items(Int.MAX_VALUE) { index ->
            val game = games[index % games.size]
            CarouselImage(game.imageId, game.gameTitle, game.contentDescription)
        }
    }

    Text(currentGame.gameTitle)
    Text(currentGame.gameDescription)
    if (currentGame.previousRoute != null){
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            NavigationButton(
                text = "Play",
                navController = navController,
                route = currentGame.playRoute
            )
            NavigationButton(
                text = "Previous",
                navController = navController,
                route = currentGame.previousRoute!!
            )
        }
    }else{
        NavigationButton(
            text = "Play",
            navController = navController,
            route = currentGame.playRoute
        )
    }
}