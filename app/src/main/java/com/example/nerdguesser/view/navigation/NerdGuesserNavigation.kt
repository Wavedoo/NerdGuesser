package com.example.nerdguesser.view.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.toRoute
import com.example.nerdguesser.view.screens.AnimeListScreen
import com.example.nerdguesser.view.screens.GuessAnimeScreen
import com.example.nerdguesser.view.screens.SignInScreen
import com.example.nerdguesser.view.screens.SignUpScreen
import kotlinx.serialization.Serializable

@Serializable
object SignInRoute

@Serializable
object SignUpRoute

@Serializable
object AnimeGuesserListRoute

@Serializable
data class AnimeGuesserGameRoute(val id: String)

//I probably don't need this file, but it's fineeeee
//Forgive the informal late night comments. It's just a fun little project anyway.

fun NavGraphBuilder.signIn(
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
){
    composable<SignInRoute> {
        SignInScreen(
            navigateToHome = navigateToHome,
            navigateToSignUp = navigateToSignUp
        )
    }
}

fun NavGraphBuilder.signUp(
    navigateToSignIn: () -> Unit
){
    composable<SignUpRoute> {
        SignUpScreen(navigateToSignIn = navigateToSignIn)
    }
}

fun NavGraphBuilder.animeGuesserList(onNavigateToGame: (String) -> Unit = {}){
    composable<AnimeGuesserListRoute> {
        AnimeListScreen(onCardClick = onNavigateToGame)
    }
}

fun NavGraphBuilder.animeGuesserGame(){
    composable<AnimeGuesserGameRoute> { backStackEntry ->
        val animeGuesserGame: AnimeGuesserGameRoute = backStackEntry.toRoute()
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

//Assumes I'm navigating to sign in should not have something before it in the backstack
fun NavController.navigateToSignIn(){
    navigate(SignInRoute, navOptions = navOptions { popUpTo(0) })
}

//Assumes I'm navigating from SignIn so it's the only other thing in the stack.
fun NavController.navigateToSignUp(){
    navigate(SignUpRoute){ launchSingleTop = true}
}
fun NavController.navigateToList(){
    navigate(
        route = AnimeGuesserListRoute,
        navOptions = navOptions {
            popUpTo(0)
        }
    )
}
fun NavController.navigateToGame(id: String){
    //dataTest.id = id
    Log.d("Anime", "navigateToGame is $id\n" +
            "Called form ...")
    navigate(AnimeGuesserGameRoute(id = id)){ launchSingleTop = true}
}