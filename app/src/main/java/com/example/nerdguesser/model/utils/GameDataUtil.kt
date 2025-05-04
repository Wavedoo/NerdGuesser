package com.example.nerdguesser.model.utils

import com.example.nerdguesser.model.classes.GameData
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.toObject

object GameDataUtil {
    val test: GameData = GameData(
        "f", "frieren", 1, listOf("fa", "Fas", "fsaf", "Fdsfdas", "fasdfasd"),
        imageFolder = "TODO()",
        totalGuesses = 0,
        firstFrameGuesses = 0,
        secondFrameGuesses = 0,
        thirdFrameGuesses = 0,
        fourthFrameGuesses = 0,
        fifthFrameGuesses = 0,
        sixthFrameGuesses = 0,
        failedGuesses = 0
    )

    fun documentToGameData(document: DocumentSnapshot): GameData{
        val data = document.data!!
        /*val test = document.toObject<GameData>()!!
        return test*/
        return GameData(
            id = document.id,
            name = data["name"] as String,
            day = (data["day"] as Long).toInt(),
            hints = data["hints"] as List<String>,
            imageFolder = data["imageFolder"] as String,
            totalGuesses = (data["totalGuesses"] as Long).toInt(),
            firstFrameGuesses = (data["firstFrameGuesses"] as Long).toInt(),
            secondFrameGuesses = (data["secondFrameGuesses"] as Long).toInt(),
            thirdFrameGuesses = (data["thirdFrameGuesses"] as Long).toInt(),
            fourthFrameGuesses = (data["fourthFrameGuesses"] as Long).toInt(),
            fifthFrameGuesses = (data["fifthFrameGuesses"] as Long).toInt(),
            sixthFrameGuesses = (data["sixthFrameGuesses"] as Long).toInt(),
            failedGuesses = (data["failedGuesses"] as Long).toInt()
        )
    }
}