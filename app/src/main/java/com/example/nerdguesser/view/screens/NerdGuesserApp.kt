package com.example.nerdguesser.view.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.nerdguesser.ui.theme.NerdGuesserTheme
import com.example.nerdguesser.view.navigation.animeGuesserGame
import com.example.nerdguesser.view.navigation.AnimeGuesserList
import com.example.nerdguesser.view.navigation.animeGuesserList
import com.example.nerdguesser.view.navigation.navigateToGame

@Composable
fun NerdGuesserApp(){
    NerdGuesserTheme(dynamicColor = false){
        val navController = rememberNavController()
        //GuessAnimeScreen("test")
        //LoadingScaffold()
        //TestScreen()
        NavHost(navController, startDestination = AnimeGuesserList){
            animeGuesserList(onNavigateToGame = {
                navController.navigateToGame(it)
            })
            animeGuesserGame()
            //nerdGuesserNavGraph(onGameSelected = {navController.navigateToGameTest("test")})
        }
    }
}
