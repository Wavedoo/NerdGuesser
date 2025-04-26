package com.example.nerdguesser.view.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.nerdguesser.R
import com.example.nerdguesser.ui.theme.NerdGuesserTheme
import com.example.nerdguesser.view.components.NerdGuesserScaffold
import com.example.nerdguesser.view.navigation.AnimeGuesserGame
import com.example.nerdguesser.view.navigation.AnimeGuesserList
import com.example.nerdguesser.view.navigation.navigateToGameTest
import com.example.nerdguesser.view.navigation.nerdGuesserNavGraph

object dataTest {
    var id: String = ""
}
@Composable
fun NerdGuesserApp(){
    NerdGuesserTheme(dynamicColor = false){
        val navController = rememberNavController()
        NavHost(navController, startDestination = AnimeGuesserList){
            AnimeGuesserList(onNavigateToGame = {navController.navigateToGameTest(it)})
            AnimeGuesserGame()
            //nerdGuesserNavGraph(onGameSelected = {navController.navigateToGameTest("test")})
        }
    }
}
