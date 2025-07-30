package com.example.nerdguesser.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.nerdguesser.view.components.EmailOutlinedTextField
import com.example.nerdguesser.view.components.NerdGuesserScaffold
import com.example.nerdguesser.view.components.PasswordOutlinedTextField
import com.example.nerdguesser.view.components.buttons.GenericButton
import com.example.nerdguesser.view.navigation.ScreenRoute
import com.example.nerdguesser.view.navigation.navigateToHome
import com.example.nerdguesser.viewmodel.SignInViewModel

//TODO: Loading indicator

@Composable
fun SignInScreen(
    //TODO: Figure out what home is
    navController: NavController,
    signInViewModel: SignInViewModel = hiltViewModel()
){
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val signInUiState by signInViewModel.uiState.collectAsStateWithLifecycle()
    val signedIn by signInViewModel.signedIn.collectAsStateWithLifecycle()
    if(signedIn){
        navController.navigateToHome()
    }

    val snackbarHostState = remember { SnackbarHostState() }
    NerdGuesserScaffold(
        title = "Nerd Guesser",
        onBackClick = {},
        hostState = snackbarHostState
    ) { innerPadding ->
        //TODO: Figure out if there's a better way to do this down the line.
        //First make it, then make it good later.
        signInUiState.snackbarError?.let { message ->
            LaunchedEffect(message) {
                snackbarHostState.showSnackbar(message)
                signInViewModel.snackbarErrorShown()
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Log in", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(32.dp))
            EmailOutlinedTextField(
                modifier = Modifier.padding(8.dp),
                label = "email",
                isError = signInUiState.emailError,
                supportingText = signInUiState.emailErrorMessage,
                value = email,
                onValueChange = {email = it}
            )
            PasswordOutlinedTextField(
                modifier = Modifier.padding(8.dp),
                label = "Password",
                isError = signInUiState.passwordError,
                supportingText = signInUiState.passwordErrorMessage,
                value = password,
                onValueChange = {password = it}
            )
            GenericButton(
                text = "Sign in",
                onClick = {
                    signInViewModel.signIn(email, password){
                        navController.navigateToHome()
                    }
                }
            )
            TextButton(
                onClick = { navController.navigate(ScreenRoute.SignUpRoute) }
            ) {
                Text("Don't have an account?\nClick here to sign up!", textAlign = TextAlign.Center)
            }
        }
    }
}