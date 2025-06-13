package com.example.nerdguesser.model.datasource

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class ImageDataSource @Inject constructor(
    private val storage: FirebaseStorage
) {
    private val FIVE_MEGABYTES: Long = 5 * 1024 * 1024
    private val storageRef = storage.reference
    private val animeGameStorage = storageRef.child("AnimeFrameGuesser")

    private suspend fun getImage(path: StorageReference): ImageBitmap {
        Log.d("Anime", "ImageDataSource.getImage()\n\tStorageReference: $path")

        val image = path.getBytes(FIVE_MEGABYTES).await()
        val bitmap: ImageBitmap = BitmapFactory.decodeByteArray(image, 0, image.size).asImageBitmap()
        return bitmap
    }

    suspend fun getImages(folderName: String): List<ImageBitmap>{
        Log.d("Anime", "ImageDataSource.getImages()\n\tfolder: $folderName")

        val folderReference = animeGameStorage.child(folderName);
        Log.d("Anime", "FolderReference: $folderReference")
        val images: MutableList<ImageBitmap> = mutableListOf()
        val references = folderReference.listAll().await()
        Log.d("Anime", "getImages.references.items = ${references.items}")
        for (ref in references.items){
            Log.d("Anime", "ref = $ref")
            images.add(getImage(ref))
            Log.d("Anime", "")
        }
        return images
    }
}