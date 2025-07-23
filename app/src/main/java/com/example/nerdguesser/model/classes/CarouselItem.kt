package com.example.nerdguesser.model.classes

import androidx.annotation.DrawableRes

data class CarouselItem(
    val id: Int,
    @DrawableRes val imageId: Int,
    val gameTitle: String,
    val gameDescription: String,
    val contentDescription: String,
    val dailyScreenRoute: String = "null"
)