package com.example.nerdguesser.model.datasource

import android.util.Log
import com.example.nerdguesser.model.classes.GameData
import com.example.nerdguesser.model.utils.GameDataUtil
import com.google.firebase.firestore.FirebaseFirestore
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

    suspend fun getGamesList(): List<String>{
        val document = firestore.collection("AnimeFrameDays").document("list").get().await()
        val data = document.data!!["IDs"] as List<String>
        return data
    }

    //TODO: look into best nullability practices
    suspend fun getGameData(id: String): GameData {
        /*delay(2000)
        return GameDataUtil.test*/
        val document = firestore.collection("AnimeFrameGuesser").document(id).get().await()
        val gameData = GameDataUtil.documentToGameData(document)
        Log.d("Anime", "Datasource: $gameData")
        return gameData
    }
}