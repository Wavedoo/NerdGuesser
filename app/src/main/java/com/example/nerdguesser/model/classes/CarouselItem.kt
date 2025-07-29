package com.example.nerdguesser.model.classes

import androidx.annotation.DrawableRes
import com.example.nerdguesser.view.navigation.ScreenRoute

data class CarouselItem(
    val id: Int,
    @DrawableRes val imageId: Int,
    val gameTitle: String,
    val gameDescription: String,
    val contentDescription: String,
    val playRoute: ScreenRoute,
    val previousRoute: ScreenRoute? = null,
    val hasDaily: Boolean = true
)