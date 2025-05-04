package com.example.nerdguesser.model.firebase

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import com.example.nerdguesser.model.classes.GameData
import com.example.nerdguesser.model.utils.GameDataUtil
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class FirestoreService {
    private val firestore = Firebase.firestore

    @Suppress("UNCHECKED_CAST")
    fun getGamesList(onListCreated: (MutableList<String>) -> Unit){
        firestore.collection("AnimeFrameDays").document("list")
            .get()
            .addOnSuccessListener {  result ->
                val ids = result.data!!["IDs"] as MutableList<String>
                Log.d("Anime", "IDs: $ids")
                onListCreated(ids)
            }
            .addOnFailureListener { exception ->
                Log.d("anime", "Error getting document: ", exception)
            }
    }

    fun getGameData(id: String, onDataRetrieved: (GameData) -> Unit){
        firestore.collection("AnimeFrameGuesser").document(id)
            .get()
            .addOnSuccessListener { result ->
                Log.d("Anime", "result: $result")
                Log.d("Anime", "data: ${result.data}")
                val data = GameDataUtil.documentToGameData(result)
                Log.d("Anime", "Data: $data")
                //TODO: look into best nullability practices
                onDataRetrieved(data!!)
            }
            .addOnFailureListener { exception ->
                Log.d("anime", "Error getting document: ", exception)
            }

    }
    suspend fun awaitGamesList(): GameData? {
        return firestore.collection("AnimeFrameGuesser").document(/*id*/).get().await().toObject()
    }

    suspend fun awaitGameData(id: String): GameData {
        val document = firestore.collection("AnimeFrameGuesser").document(id).get().await()
        val gameData = GameDataUtil.documentToGameData(document)
        Log.d("Anime", "Game data retrieved: $gameData")
        return gameData
    }
}
//TODO class im tired