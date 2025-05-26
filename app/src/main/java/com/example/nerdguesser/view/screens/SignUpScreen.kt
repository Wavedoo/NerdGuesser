package com.example.nerdguesser.view.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nerdguesser.view.components.EmailOutlinedTextField
import com.example.nerdguesser.view.components.NerdGuesserScaffold
import com.example.nerdguesser.view.components.PasswordOutlinedTextField
import com.example.nerdguesser.view.components.buttons.GenericButton
import com.example.nerdguesser.viewmodel.SignInViewModel
import com.example.nerdguesser.viewmodel.SignUpViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun SignUpScreen(
    navigateToSignIn: () -> Unit,
    navigateToHome: () -> Unit,
    signUpViewModel: SignUpViewModel = hiltViewModel()
){
    var email by rememberSaveable { mutableStateOf("test@test.com") }
    var password by rememberSaveable { mutableStateOf("animes") }
    var confirmPassword by rememberSaveable { mutableStateOf("animes") }

    val signUpUiState by signUpViewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    if(signUpViewModel.isSignedIn()){
        navigateToHome()
    }
    NerdGuesserScaffold(
        title = "Nerd Guesser",
        onBackClick = {}
    ) { innerPadding ->
        //
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Sign up", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(32.dp))
            EmailOutlinedTextField(
                modifier = Modifier.padding(8.dp),
                label = "email",
                isError = signUpUiState.emailError,
                supportingText = if (!signUpUiState.emailError) "*required" else signUpUiState.emailErrorMessage,
                value = email,
                onValueChange = {email = it}
            )
            PasswordOutlinedTextField(
                modifier = Modifier.padding(8.dp),
                label = "Password",
                isError = signUpUiState.passwordError,
                supportingText = if (!signUpUiState.passwordError) "*required" else signUpUiState.passwordErrorMessage,
                value = password,
                onValueChange = {password = it},
                imeAction = ImeAction.Next
            )
            PasswordOutlinedTextField(
                modifier = Modifier.padding(8.dp),
                label = "Confirm password",
                isError = signUpUiState.confirmError,
                supportingText = if (!signUpUiState.confirmError) "*required" else signUpUiState.confirmErrorMessage,
                value = confirmPassword,
                onValueChange = {confirmPassword = it}
            )
            GenericButton(
                text = "Sign up",
                onClick = {
                    signUpViewModel.signUp(email, password, confirmPassword, navigateToHome) {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                }
            )
            TextButton(
                onClick = navigateToSignIn
            ) {
                Text("Already have an account?\nClick here to log in!", textAlign = TextAlign.Center)
            }
        }
    }
}