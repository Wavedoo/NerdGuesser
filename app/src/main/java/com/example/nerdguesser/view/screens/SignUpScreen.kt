package com.example.nerdguesser.view.screens

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.nerdguesser.view.components.EmailOutlinedTextField
import com.example.nerdguesser.view.components.NerdGuesserScaffold
import com.example.nerdguesser.view.components.PasswordOutlinedTextField
import com.example.nerdguesser.view.components.buttons.GenericButton

@Composable
fun SignUpScreen(
    navigateToSignIn: () -> Unit
){
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

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
                supportingText = "*required",
                value = email,
                onValueChange = {email = it}
            )
            PasswordOutlinedTextField(
                modifier = Modifier.padding(8.dp),
                label = "Password",
                supportingText = "*required",
                value = password,
                onValueChange = {password = it}
            )
            PasswordOutlinedTextField(
                modifier = Modifier.padding(8.dp),
                label = "Confirm password",
                supportingText = "*required",
                value = confirmPassword,
                onValueChange = {confirmPassword = it}
            )
            GenericButton(text = "Sign up", onClick = {})
            TextButton(
                onClick = navigateToSignIn
            ) {
                Text("Already have an account?\nClick here to log in!", textAlign = TextAlign.Center)
            }
        }
    }
}