package com.example.nerdguesser.model.classes

class AnswerData(
    val name: String,
    val images: List<Int> /*For now just gonna use R.Drawable image id things*/,
    val hints: Hints
)