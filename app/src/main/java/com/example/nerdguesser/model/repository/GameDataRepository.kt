package com.example.nerdguesser.model.repository

import com.example.nerdguesser.model.classes.GameData
import com.example.nerdguesser.model.datasource.GameDataDataSource
import javax.inject.Inject

class GameDataRepository @Inject constructor(
    private val gameDataDataSource: GameDataDataSource
) {
    //TODO: Learn how to handle failures like this
    suspend fun getGamesList(): List<String> {
        return gameDataDataSource.getGamesList()
    }

    //TODO: Learn how to handle if id cant be found
    suspend fun getGameData(id: String): GameData {
        return gameDataDataSource.getGameData(id)
    }
}