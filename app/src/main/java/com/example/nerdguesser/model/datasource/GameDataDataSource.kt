package com.example.nerdguesser.model.datasource

import android.util.Log
import com.example.nerdguesser.GameDataMissingException
import com.example.nerdguesser.InvalidDayException
import com.example.nerdguesser.model.classes.GameData
import com.example.nerdguesser.model.utils.GameDataUtil
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlinx.coroutines.delay


class GameDataDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    //Compare to https://github.com/FirebaseExtended/make-it-so-android/blob/main/v2/app/src/main/java/com/google/firebase/example/makeitso/data/datasource/TodoItemRemoteDataSource.kt
    //I probably don't need flow
    /*fun getGamesList(): Flow<List<GameData>>{

    }*/

    val gameCollectionRef = firestore.collection("AnimeFrameGuesser")
    suspend fun getGamesList(): List<String>{
        val document = firestore.collection("AnimeInformation").document("FrameDays").get().await()
        val data = document.data!!["IDs"] as List<String>
        return data
    }

    //TODO: look into best nullability practices
    suspend fun getGameData(id: String): GameData {
        /*delay(2000)
        return GameDataUtil.test*/
        val document = gameCollectionRef.document(id).get().await()
        //val gameData = GameDataUtil.documentToGameData(document)
        val gameData = document.toObject<GameData>()!!
        Log.d("Anime", "Datasource: $gameData")
        return gameData
    }

    suspend fun getGameData(day: Int): GameData {
        val query = gameCollectionRef.whereEqualTo("day", day).whereEqualTo("enabled", true)
        val docs = query.get().await()
        if(docs.documents.size < 1){
            throw InvalidDayException()
        }
        val gameData = docs.documents[0].toObject<GameData>()
        if(gameData?.isValidData() != true){
            throw GameDataMissingException()
        }
        return gameData
    }

    //-1 represents failed guesses
    fun updateGameGuesses(id: String, guesses: Int){
        val field = getFieldFromGuess(guesses)
        val documentRef = firestore.collection("AnimeFrameGuesser").document(id)
        Log.d("Anime", "Attempting to update $id\n$documentRef")
        documentRef.update(field, FieldValue.increment(1), "totalGuesses", FieldValue.increment(1))
            .addOnSuccessListener { Log.d("Anime", "Updated $id successfully!") }
            .addOnFailureListener { Log.d("Anime", "Failed to update $id :(") }
    }

    private fun getFieldFromGuess(guesses: Int): String{
        return when(guesses){
            -1 -> "failedGuesses"
            1 -> "firstFrameGuesses"
            2 -> "secondFrameGuesses"
            3 -> "thirdFrameGuesses"
            4 -> "fourthFrameGuesses"
            5 -> "fifthFrameGuesses"
            6 -> "sixthFrameGuesses"
            //Figure out how to properly handle an incorrect number
            else -> "failedGuesses"
        }
    }
}