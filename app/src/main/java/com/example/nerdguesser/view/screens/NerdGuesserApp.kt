package com.example.nerdguesser.view.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.nerdguesser.ui.theme.NerdGuesserTheme
import com.example.nerdguesser.view.navigation.animeGuesserGame
import com.example.nerdguesser.view.navigation.SignInRoute
import com.example.nerdguesser.view.navigation.SignUpRoute
import com.example.nerdguesser.view.navigation.animeGuesserList
import com.example.nerdguesser.view.navigation.navigateToGame
import com.example.nerdguesser.view.navigation.navigateToList
import com.example.nerdguesser.view.navigation.navigateToSignIn
import com.example.nerdguesser.view.navigation.navigateToSignUp
import com.example.nerdguesser.view.navigation.signIn
import com.example.nerdguesser.view.navigation.signUp

@Composable
fun NerdGuesserApp(){
    NerdGuesserTheme(dynamicColor = false){
        val navController = rememberNavController()
        //GuessAnimeScreen("test")
        //LoadingScaffold()
        //TestScreen()
        NavHost(navController, startDestination = SignUpRoute){
            signIn(
                navigateToHome = { navController.navigateToList() },
                navigateToSignUp = {navController.navigateToSignUp()}
            )
            signUp(
                navigateToSignIn = {navController.navigateToSignIn()}
            )
            animeGuesserList(onNavigateToGame = {
                navController.navigateToGame(it)
            })
            animeGuesserGame()
            //nerdGuesserNavGraph(onGameSelected = {navController.navigateToGameTest("test")})
        }
    }
}
