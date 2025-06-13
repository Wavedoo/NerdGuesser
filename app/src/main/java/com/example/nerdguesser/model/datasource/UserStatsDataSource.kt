package com.example.nerdguesser.model.datasource

import com.example.nerdguesser.model.repository.AuthRepository
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class UserStatsDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
) {
    fun updateStats(){

    }
}