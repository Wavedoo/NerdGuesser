package com.example.nerdguesser.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nerdguesser.view.components.buttons.GenericButton
import com.example.nerdguesser.view.components.buttons.NavigationButton
import com.example.nerdguesser.view.navigation.ScreenRoute
import com.example.nerdguesser.view.navigation.navigateToHome
import com.example.nerdguesser.viewmodel.WelcomeViewModel

@Composable
fun WelcomeScreen(
    navController: NavController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
){
    val signedIn by welcomeViewModel.signedIn.collectAsStateWithLifecycle()
    if(signedIn){
        navController.navigateToHome()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.navigationBars)
            .background(Color.White)
            .padding(16.dp)
    ) {
        NavigationButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Sign In or Sign Up",
            navController = navController,
            route = ScreenRoute.SignInRoute
        )
        GenericButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Continue as guest",
            onClick = { welcomeViewModel.createGuestAccount() }
        )
    }
}

@Composable
@Preview
fun WelcomeScreenPreview(){
    WelcomeScreen(
        navController = rememberNavController()
    )
}