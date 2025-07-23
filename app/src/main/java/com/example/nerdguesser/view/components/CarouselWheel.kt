package com.example.nerdguesser.view.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.HorizontalCenteredHeroCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.example.nerdguesser.model.classes.CarouselItem

@ExperimentalMaterial3Api
@Composable
fun CarouselWheel(games: List<CarouselItem>){
    HorizontalCenteredHeroCarousel(
        state = rememberCarouselState {games.count() },
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 16.dp),
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp),
        minSmallItemWidth = 30.dp,
        maxSmallItemWidth = 30.dp
    ) { i ->
        val game = games[i]
        CarouselImage(game.imageId, game.gameTitle, game.contentDescription)
    }
}