package com.example.nerdguesser.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nerdguesser.view.components.NerdGuesserScaffold
import com.example.nerdguesser.view.components.buttons.GenericButton
import com.example.nerdguesser.viewmodel.UserInfoTestViewModel

@Composable
fun UserInfoTestScreen(
    onSignOut: () -> Unit,
    navigateToGames: () -> Unit,
    userInfoTestViewModel: UserInfoTestViewModel = hiltViewModel()
){
    val user = userInfoTestViewModel.getUser()
    if(user == null){
        onSignOut()
    }
    user!!
    NerdGuesserScaffold(
        title = "User Info",
        onBackClick = onSignOut
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "User: $user")
            Text(text = "UID: " + user.uid)
            Text(text = "Email: " + user.email.toString())
            Text(text = "Anonymous: " + user.isAnonymous.toString())
            Text(text = "Display Name: " + user.displayName.toString())
            Text(text = "Metadata: " + user.metadata.toString())
            Text(text = "Provider Data: " + user.providerData.toString())
            Text(text = "Multifactor: " + user.multiFactor.toString())
            GenericButton(
                text = "Sign Out",
                onClick = {
                    userInfoTestViewModel.signOut()
                    onSignOut()
                }
            )
            GenericButton(
                text = "Games",
                onClick = navigateToGames
            )
        }
    }
}