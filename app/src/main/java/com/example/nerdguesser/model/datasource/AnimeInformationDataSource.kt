package com.example.nerdguesser.model.datasource

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AnimeInformationDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
){
    suspend fun getGamesList(): List<String>{
        val document = firestore.collection("AnimeInformation").document("FrameDays").get().await()
        val data = document.data!!["IDs"] as List<String>
        return data
    }

    suspend fun getAnimeList(): List<String>{
        val document = firestore.collection("AnimeInformation").document("AnimeNames").get().await()
        val data = document.data!!["names"] as List<String>
        return data
    }
}