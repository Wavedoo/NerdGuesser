package com.example.nerdguesser.model.classes

//TODO: @Serializable?
//TODO: Different classes for business and UI (i.e. AnswerDataFirebaseModel, AnswerData)
//TODO: Change to GameData
class AnswerData(
    val name: String,
    val images: List<Int> /*For now just gonna use R.Drawable image id things*/,
    val hints: Hints
)