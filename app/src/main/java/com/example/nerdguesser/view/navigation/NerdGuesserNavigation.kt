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
import com.example.nerdguesser.view.screens.HomeScreen
import com.example.nerdguesser.view.screens.SettingsScreen
import com.example.nerdguesser.view.screens.SignInScreen
import com.example.nerdguesser.view.screens.SignUpScreen
import com.example.nerdguesser.view.screens.UserInfoTestScreen
import kotlinx.serialization.Serializable

@Serializable
object SignInRoute

@Serializable
object SignUpRoute

@Serializable
object UserInfoTestRoute

@Serializable
object AnimeGuesserListRoute

@Serializable
data class AnimeGuesserGameRoute(val id: String)

@Serializable
object HomeRoute

@Serializable
object SettingsRoute

//I probably don't need this file, but it's fineeeee
//Forgive the informal late night comments. It's just a fun little project anyway.

//TODO: https://github.com/android/compose-samples/blob/bec669f48e981887ff09c2162cef4f64299fb0dc/Jetsnack/app/src/main/java/com/example/jetsnack/ui/home/Home.kt


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
    navigateToSignIn: () -> Unit,
    navigateToUserInfo: () -> Unit
){
    composable<SignUpRoute> {
        SignUpScreen(navigateToSignIn = navigateToSignIn, navigateToHome = navigateToUserInfo)
    }
}

fun NavGraphBuilder.userInfoTest(
    onSignOut: () -> Unit,
    onNavigateToList: () -> Unit
){
    composable<UserInfoTestRoute> {
        UserInfoTestScreen(onSignOut = onSignOut, navigateToGames = onNavigateToList)
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

fun NavGraphBuilder.homeScreen(
    onNavigateToList: () -> Unit,
    onNavigateToSettings: () -> Unit
){
    composable<HomeRoute> {
        HomeScreen(
            navigateToGames = onNavigateToList,
            navigateToSettings = onNavigateToSettings,
        )
    }
}

fun NavGraphBuilder.settingsScreen(
    onSignOut: () -> Unit
){
    composable<SettingsRoute> {
        SettingsScreen(
            onSignOut = onSignOut
        )
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

fun NavController.navigateToUserInfo(){
    navigate(
        route = UserInfoTestRoute,
        navOptions = navOptions {
            popUpTo(0)
        }
    )
}

fun NavController.navigateToList(){
    navigate(
        route = AnimeGuesserListRoute,
        /*navOptions = navOptions {
            popUpTo(0)
        }*/
    )
}
fun NavController.navigateToGame(id: String){
    //dataTest.id = id
    Log.d("Anime", "navigateToGame is $id\n" +
            "Called form ...")
    navigate(AnimeGuesserGameRoute(id = id)){ launchSingleTop = true}
}

fun NavController.navigateToHome(){
    navigate(route = HomeRoute) { launchSingleTop = true}
}

fun NavController.navigateToSettings(){
    navigate(route = SettingsRoute) { launchSingleTop = true}
}