package com.example.nerdguesser.model.firebase

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.component1
import com.google.firebase.storage.component2

object FirebaseStorageTest {
    val storage = Firebase.storage

    fun getAllFiles(path: String){
        var gsReference = storage.getReferenceFromUrl(path);
        //val listRef = storage.reference.child("")
        Log.d("Anime", "gsReference: $gsReference")
        FirebaseStorage.getInstance()
        gsReference.listAll()
            .addOnSuccessListener { (items, prefixes) ->
                for (prefix in prefixes) {
                    // All the prefixes under listRef.
                    // You may call listAll() recursively on them.
                    Log.d("Anime", "Prefix: ${prefix}")
                }

                for (item in items) {
                    Log.d("Anime", "item: ${item}")
                }
            }
            .addOnFailureListener {
                Log.d("Anime", "Failure")
            }
    }

    fun findFileType(path: String): String{

        var gsReference = storage.getReferenceFromUrl("$path.jpg")
        var found: Boolean = false
        gsReference.metadata.addOnSuccessListener {
            it ->
            found = true
        }
        if(found) return "$path.jpg"

        gsReference = storage.getReferenceFromUrl("$path.png")
        gsReference.metadata.addOnSuccessListener {
                it ->
            found = true
        }
        if(found) return "$path.png"
        gsReference = storage.getReferenceFromUrl("$path.jpeg")
        gsReference.metadata.addOnSuccessListener {
                it ->
            found = true
        }
        if(found) return "$path.jpeg"
        return path
    }
}