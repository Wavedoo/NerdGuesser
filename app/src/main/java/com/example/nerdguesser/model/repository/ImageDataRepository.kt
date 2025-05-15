package com.example.nerdguesser.model.repository

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import com.example.nerdguesser.model.datasource.ImageDataSource
import javax.inject.Inject

class ImageDataRepository @Inject constructor(
    private val imageDataSource: ImageDataSource
) {
    //TODO: Cache the images, save for later cause i really don't want to do this right now
    suspend fun getImages(folder: String): List<ImageBitmap> {
        val result = imageDataSource.getImages(folder)
        Log.d("Anime", "ImageDataRepository.getImages($folder) = ${result.size}, $result")
        return result

    }
}