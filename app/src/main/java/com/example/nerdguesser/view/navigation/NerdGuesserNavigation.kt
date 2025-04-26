package com.example.nerdguesser.view.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.nerdguesser.view.screens.AnimeListScreen
import com.example.nerdguesser.view.screens.GuessAnimeScreen
import com.example.nerdguesser.view.screens.dataTest
import kotlinx.serialization.Serializable

@Serializable
object AnimeGuesserList

@Serializable
object AnimeGuesserGame


fun NavGraphBuilder.AnimeGuesserList(onNavigateToGame: (String) -> Unit = {}){
    composable<AnimeGuesserList> {
        AnimeListScreen(onCardClick = onNavigateToGame)
    }
}

fun NavGraphBuilder.AnimeGuesserGame(id: String = ""){
    composable<AnimeGuesserGame> {
        GuessAnimeScreen()
    }
}

//Extracted like this, because I'd prefer navGraphBuilder not looking messy in NavHost Lambda
//Unextracted (or is it tracted) cause I don't want to have to pass a function into this then into AnimeGuesserList
fun NavGraphBuilder.nerdGuesserNavGraph(onGameSelected: (String) -> Unit = {}){
    AnimeGuesserList(onNavigateToGame = onGameSelected)
    AnimeGuesserGame()
}

fun NavGraphBuilder.Test2(){
    AnimeGuesserList()
    composable<AnimeGuesserList> {
        AnimeListScreen()
    }
    composable<AnimeGuesserGame> {
        GuessAnimeScreen()
    }
}

fun NavController.navigateToGameTest(id: String = "Frieren"){
    dataTest.id = id
    navigate(AnimeGuesserGame)
}