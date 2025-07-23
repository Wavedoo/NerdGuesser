package com.example.nerdguesser.view.components

import android.util.Log
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.CarouselDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nerdguesser.model.classes.CarouselItem
import kotlinx.coroutines.coroutineScope

/*
This dosen't need to be a wheel at all,
it might actually be worse.
But I did it because I could.
*/
@Composable
fun GameWheel(
    lazyListState: LazyListState,
    games: List<CarouselItem>
){
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    //TODO: Figure out why lazyliststate is causes multiople recompositions and/or ifit's oaky
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
}