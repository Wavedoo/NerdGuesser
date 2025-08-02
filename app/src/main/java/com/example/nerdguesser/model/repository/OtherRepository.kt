package com.example.nerdguesser.model.repository

import com.example.nerdguesser.model.datasource.OtherDataSource
import javax.inject.Inject

class OtherRepository @Inject constructor(
    private val otherDataSource: OtherDataSource
) {
    suspend fun getAnimeCount(): Int{
        return otherDataSource.getAnimeCount()
    }
}