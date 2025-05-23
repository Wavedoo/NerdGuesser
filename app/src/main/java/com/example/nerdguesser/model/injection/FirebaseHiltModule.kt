package com.example.nerdguesser.model.injection

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//It's 3am, learn more about this at a less tired time: https://developer.android.com/training/dependency-injection/hilt-android#generated-components

@Module
@InstallIn(SingletonComponent::class)
object FirebaseHiltModule {
    @Provides fun firestore(): FirebaseFirestore = Firebase.firestore

    @Provides fun storage(): FirebaseStorage = Firebase.storage

    @Provides fun auth(): FirebaseAuth = Firebase.auth
}