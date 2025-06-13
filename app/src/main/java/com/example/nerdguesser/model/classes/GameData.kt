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
/*
Decided to leave it as is, damage is probably minimal and it allows for it to be sorted by easiest to hardest by using firstFrameGuesses divided by failedGuesses?
completionRate/failureRate
firstFrameGuessRate
 */
@Serializable
data class GameData(
    @DocumentId val id: String = "",
    val name: String = "",
    val day: Int = -1,
    //val enabled: Boolean
    val hints: List<String> = listOf(), /*rating, demographic, release date, genres, studios*/
    val folderName: String = "",
    val totalGuesses: Int = 0,
    val firstFrameGuesses: Int = 0,
    val secondFrameGuesses: Int = 0,
    val thirdFrameGuesses: Int = 0,
    val fourthFrameGuesses: Int = 0,
    val fifthFrameGuesses: Int = 0,
    val sixthFrameGuesses: Int = 0,
    val failedGuesses: Int = 0 ,
    val successRate: Double = 0.0, //Can be used as failure rate
    val firstFrameRate: Double = 0.0 // I probably don't need any other rates than this.
    //val imageLinks: List<String>
){
    override fun toString() = "id = $id\n\tname = $name\n" +
            "\tday = $day\n" +
            "\thints = $hints\n" +
            "\timagesFolder = $folderName"

}