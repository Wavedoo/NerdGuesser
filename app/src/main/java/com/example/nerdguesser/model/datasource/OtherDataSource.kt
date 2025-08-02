package com.example.nerdguesser.model.datasource

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class OtherDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val otherRef = firestore.collection("other")
    private val globalRef = otherRef.document("global")
    suspend fun getAnimeCount(): Int {
        val doc = globalRef.get().await()
        val count = doc.data?.get("animeDay") as Long
        return (count ?: 0).toInt()
    }
}