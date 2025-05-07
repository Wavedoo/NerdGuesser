package com.example.nerdguesser.model.datasource

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.nerdguesser.R
import com.google.api.Context
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.component1
import com.google.firebase.storage.component2
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class ImageDataSource @Inject constructor(
    private val storage: FirebaseStorage
) {
    private val FIVE_MEGABYTES: Long = 5 * 1024 * 1024

    private suspend fun getImage(path: StorageReference): ImageBitmap {
        Log.d("Anime", "ImageDataSource.getImage()\n\tStorageReference: $path")

        val image = path.getBytes(FIVE_MEGABYTES).await()
        val bitmap: ImageBitmap = BitmapFactory.decodeByteArray(image, 0, image.size).asImageBitmap()
        return bitmap
    }

    suspend fun getImages(imageFolder: String): List<ImageBitmap>{
        Log.d("Anime", "ImageDataSource.getImages()\n\tfolder: $imageFolder")

        val gsReference = storage.getReferenceFromUrl(imageFolder);
        val images: MutableList<ImageBitmap> = mutableListOf()
        val references = gsReference.listAll().await()
        for (ref in references.items){
            images.add(getImage(ref))
        }
        return images
    }
}