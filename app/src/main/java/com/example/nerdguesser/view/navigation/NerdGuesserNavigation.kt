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
sealed class ScreenRoute{
    @Serializable
    object SignInRoute: ScreenRoute()

    @Serializable
    object SignUpRoute: ScreenRoute()

    @Serializable
    object UserInfoTestRoute: ScreenRoute()

    @Serializable
    object AnimeGuesserListRoute: ScreenRoute()

    @Serializable
    data class AnimeGuesserGameRoute(val id: String): ScreenRoute()

    @Serializable
    object HomeRoute: ScreenRoute()

    @Serializable
    object SettingsRoute: ScreenRoute()
}



//I probably don't need this file, but it's fineeeee
//Forgive the informal late night comments. It's just a fun little project anyway.

//TODO: https://github.com/android/compose-samples/blob/bec669f48e981887ff09c2162cef4f64299fb0dc/Jetsnack/app/src/main/java/com/example/jetsnack/ui/home/Home.kt


fun NavGraphBuilder.signIn(
    navController: NavController,
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
){
    composable<ScreenRoute.SignInRoute> {
        SignInScreen(
            navController
        )
    }
}

fun NavGraphBuilder.signUp(
    navController: NavController,
    navigateToSignIn: () -> Unit,
    navigateToUserInfo: () -> Unit
){
    composable<ScreenRoute.SignUpRoute> {
        SignUpScreen(navController)
    }
}

fun NavGraphBuilder.userInfoTest(
    navController: NavController,
    onSignOut: () -> Unit,
    onNavigateToList: () -> Unit
){
    composable<ScreenRoute.UserInfoTestRoute> {
        UserInfoTestScreen(navController)
    }
}
fun NavGraphBuilder.animeGuesserList(
    navController: NavController,
    onNavigateToGame: (String) -> Unit = {}
){
    composable<ScreenRoute.AnimeGuesserListRoute> {
        AnimeListScreen(navController)
    }
}

fun NavGraphBuilder.animeGuesserGame(
    navController: NavController,
){
    composable<ScreenRoute.AnimeGuesserGameRoute> { backStackEntry ->
        val animeGuesserGame: ScreenRoute.AnimeGuesserGameRoute = backStackEntry.toRoute()
        Log.d("Anime", "animeGuesserGame: ${animeGuesserGame.id}")
        GuessAnimeScreen(navController = navController, id = animeGuesserGame.id)
    }
}

fun NavGraphBuilder.homeScreen(
    navController: NavController,
    onNavigateToList: () -> Unit,
    onNavigateToSettings: () -> Unit
){
    composable<ScreenRoute.HomeRoute> {
        HomeScreen(
            navController = navController,
            navigateToGames = onNavigateToList,
            navigateToSettings = onNavigateToSettings,
        )
    }
}

fun NavGraphBuilder.settingsScreen(
    navController: NavController,
    onSignOut: () -> Unit
){
    composable<ScreenRoute.SettingsRoute> {
        SettingsScreen(navController)
    }
}

//Extracted like this, because I'd prefer navGraphBuilder not looking messy in NavHost Lambda
//Unextracted (or is it tracted) cause I don't want to have to pass a function into this then into AnimeGuesserList
fun NavGraphBuilder.nerdGuesserNavGraph(onGameSelected: (String) -> Unit = {}){
    /*animeGuesserList(onNavigateToGame = onGameSelected)
    animeGuesserGame()*/
}

//Assumes I'm navigating to sign in should not have something before it in the backstack
fun NavController.navigateToSignIn(){
    navigate(ScreenRoute.SignInRoute, navOptions = navOptions { popUpTo(0) })
}

//Assumes I'm navigating from SignIn so it's the only other thing in the stack.
fun NavController.navigateToSignUp(){
    navigate(ScreenRoute.SignUpRoute){ launchSingleTop = true}
}

fun NavController.navigateToUserInfo(){
    navigate(
        route = ScreenRoute.UserInfoTestRoute,
        navOptions = navOptions {
            popUpTo(0)
        }
    )
}

fun NavController.navigateToList(){
    navigate(
        route = ScreenRoute.AnimeGuesserListRoute,
        /*navOptions = navOptions {
            popUpTo(0)
        }*/
    )
}
fun NavController.navigateToGame(id: String){
    //dataTest.id = id
    Log.d("Anime", "navigateToGame is $id\n" +
            "Called form ...")
    navigate(ScreenRoute.AnimeGuesserGameRoute(id = id)){ launchSingleTop = true}
}

fun NavController.navigateToHome(){
    navigate(
        route = ScreenRoute.HomeRoute,
        navOptions = navOptions {
            popUpTo(0)
        }
    )
}

fun NavController.navigateToSettings(){
    navigate(route = ScreenRoute.SettingsRoute) { launchSingleTop = true}
}