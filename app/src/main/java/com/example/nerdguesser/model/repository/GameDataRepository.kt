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
    //TODO: Error handling
    suspend fun getGameData(id: String): GameData {
        return gameDataDataSource.getGameData(id)
    }

    suspend fun getGameData(day: Int): GameData{
        return gameDataDataSource.getGameData(day)
    }
    //-1 represents failed guesses
    fun updateGameGuesses(id: String, guesses: Int){
        gameDataDataSource.updateGameGuesses(id = id, guesses = guesses)
    }
}