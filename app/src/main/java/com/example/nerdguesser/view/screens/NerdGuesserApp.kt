package com.example.nerdguesser.view.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.nerdguesser.ui.theme.NerdGuesserTheme
import com.example.nerdguesser.view.navigation.AnimeGuesserListRoute
import com.example.nerdguesser.view.navigation.animeGuesserGame
import com.example.nerdguesser.view.navigation.SignInRoute
import com.example.nerdguesser.view.navigation.SignUpRoute
import com.example.nerdguesser.view.navigation.UserInfoTestRoute
import com.example.nerdguesser.view.navigation.animeGuesserList
import com.example.nerdguesser.view.navigation.homeScreen
import com.example.nerdguesser.view.navigation.navigateToGame
import com.example.nerdguesser.view.navigation.navigateToHome
import com.example.nerdguesser.view.navigation.navigateToList
import com.example.nerdguesser.view.navigation.navigateToSettings
import com.example.nerdguesser.view.navigation.navigateToSignIn
import com.example.nerdguesser.view.navigation.navigateToSignUp
import com.example.nerdguesser.view.navigation.navigateToUserInfo
import com.example.nerdguesser.view.navigation.settingsScreen
import com.example.nerdguesser.view.navigation.signIn
import com.example.nerdguesser.view.navigation.signUp
import com.example.nerdguesser.view.navigation.userInfoTest

@Composable
fun NerdGuesserApp(){
    NerdGuesserTheme(dynamicColor = false){
        val navController = rememberNavController()
        //GuessAnimeScreen("test")
        //LoadingScaffold()
        //TestScreen()
        NavHost(navController, startDestination = SignInRoute){
            signIn(
                navigateToHome = { navController.navigateToHome() },
                navigateToSignUp = {navController.navigateToSignUp()}
            )
            signUp(
                navigateToSignIn = {navController.navigateToSignIn()},
                navigateToUserInfo = {navController.navigateToUserInfo()}
            )
            userInfoTest(
                onSignOut = { navController.navigateToSignIn() },
                onNavigateToList = {navController.navigateToList()}
            )
            animeGuesserList(onNavigateToGame = {
                navController.navigateToGame(it)
            })
            animeGuesserGame()
            homeScreen(
                onNavigateToList = { navController.navigateToList() },
                onNavigateToSettings = {navController.navigateToSettings()}
            )
            settingsScreen(
                onSignOut = {navController.navigateToSignIn()}
            )
            //nerdGuesserNavGraph(onGameSelected = {navController.navigateToGameTest("test")})
        }
    }
}
