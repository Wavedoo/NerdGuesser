package com.example.nerdguesser.model.classes

import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Serializable

//Hints are just plain english string cause it's easier
/*
TODO: Probably reaarange firestore to be like:
 AnimeFrameGuesser
 AnimeFrameResults:
    user = userid
    game = gameid (or day)
    guess = (1-7) (or -1)

 Then calls could be like:
    Get from idk i'll figure this out
 */

//TODO: Change firstGuesses to failedGuesses to an array
@Serializable
data class GameData(
    @DocumentId val id: String,
    val name: String,
    val day: Int,
    val hints: List<String>,
    val imageFolder: String,
    val totalGuesses: Int,
    val firstFrameGuesses: Int,
    val secondFrameGuesses: Int,
    val thirdFrameGuesses: Int,
    val fourthFrameGuesses: Int,
    val fifthFrameGuesses: Int,
    val sixthFrameGuesses: Int,
    val failedGuesses: Int
    //val imageLinks: List<String>
){
    override fun toString() = "id = $id\n\tname = $name\n" +
            "\tday = $day\n" +
            "\thints = $hints\n" +
            "\timagesFolder = $imageFolder"

}