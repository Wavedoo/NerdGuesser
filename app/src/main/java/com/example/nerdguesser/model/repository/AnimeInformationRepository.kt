package com.example.nerdguesser.model.repository

import com.example.nerdguesser.model.datasource.AnimeInformationDataSource
import javax.inject.Inject

class AnimeInformationRepository @Inject constructor(
    private val animeInformationDataSource: AnimeInformationDataSource
) {
    suspend fun getGamesList(): List<String>{
        return animeInformationDataSource.getGamesList()
    }

    suspend fun getAnimeList(): List<String>{
        return animeInformationDataSource.getAnimeList()
    }
}