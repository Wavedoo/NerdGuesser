package com.example.nerdguesser.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nerdguesser.view.components.EmailOutlinedTextField
import com.example.nerdguesser.view.components.NerdGuesserScaffold
import com.example.nerdguesser.view.components.PasswordOutlinedTextField
import com.example.nerdguesser.view.components.buttons.GenericButton

@Composable
fun SignInScreen(
    //TODO: Figure out what home is
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
){
    NerdGuesserScaffold(
        title = "Nerd Guesser",
        onBackClick = {}
    ) { innerPadding ->
        //w
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Log in", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(32.dp))
            EmailOutlinedTextField(label = "email", modifier = Modifier.padding(8.dp))
            PasswordOutlinedTextField(label = "Password", modifier = Modifier.padding(8.dp))
            GenericButton(text = "Sign in", onClick = {})
            TextButton(
                onClick = navigateToSignUp
            ) {
                Text("Don't have an account?\nClick here to sign up!", textAlign = TextAlign.Center)
            }
        }
    }
}