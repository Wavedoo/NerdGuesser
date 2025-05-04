package com.example.nerdguesser.view.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.nerdguesser.model.repositories.GameDataSource
import com.example.nerdguesser.model.utils.GameDataUtil
import com.example.nerdguesser.ui.theme.NerdGuesserTheme
import com.example.nerdguesser.view.navigation.animeGuesserGame
import com.example.nerdguesser.view.navigation.AnimeGuesserList
import com.example.nerdguesser.view.navigation.animeGuesserList
import com.example.nerdguesser.view.navigation.navigateToGame

object dataTest {
    var id: String = ""
}
@Composable
fun NerdGuesserApp(){
    NerdGuesserTheme(dynamicColor = false){
        val navController = rememberNavController()
        //GuessAnimeScreen("test")
        //LoadingScaffold()
        NavHost(navController, startDestination = AnimeGuesserList){
            animeGuesserList(onNavigateToGame = {
                GameDataSource.id = it
                Log.d("Anime", "GameDataSource: ${GameDataSource.id}")
                navController.navigateToGame(it)
            })
            animeGuesserGame()
            //nerdGuesserNavGraph(onGameSelected = {navController.navigateToGameTest("test")})
        }
    }
}
