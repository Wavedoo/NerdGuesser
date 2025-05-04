package com.example.nerdguesser.view.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.nerdguesser.model.classes.GameData
import com.example.nerdguesser.model.utils.GameDataUtil
import com.example.nerdguesser.view.screens.AnimeListScreen
import com.example.nerdguesser.view.screens.GuessAnimeScreen
import kotlinx.serialization.Serializable

@Serializable
object AnimeGuesserList

@Serializable
data class AnimeGuesserGame(val id: String)


fun NavGraphBuilder.animeGuesserList(onNavigateToGame: (String) -> Unit = {}){
    composable<AnimeGuesserList> {
        AnimeListScreen(onCardClick = onNavigateToGame)
    }
}

fun NavGraphBuilder.animeGuesserGame(){
    composable<AnimeGuesserGame> {backStackEntry ->
        val animeGuesserGame: AnimeGuesserGame = backStackEntry.toRoute()
        Log.d("Anime", "animeGuesserGame: ${animeGuesserGame.id}")
        GuessAnimeScreen(id = animeGuesserGame.id)
    }
}

//Extracted like this, because I'd prefer navGraphBuilder not looking messy in NavHost Lambda
//Unextracted (or is it tracted) cause I don't want to have to pass a function into this then into AnimeGuesserList
fun NavGraphBuilder.nerdGuesserNavGraph(onGameSelected: (String) -> Unit = {}){
    animeGuesserList(onNavigateToGame = onGameSelected)
    animeGuesserGame()
}

/*fun NavGraphBuilder.Test2(){
    animeGuesserList()
    composable<AnimeGuesserList> {
        AnimeListScreen()
    }
    composable<AnimeGuesserGame> {
        GuessAnimeScreen()
    }
}*/

fun NavController.navigateToGame(id: String){
    //dataTest.id = id
    Log.d("Anime", "navigateToGame is $id")
    navigate(AnimeGuesserGame(id = id))
}