package com.example.nerdguesser.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nerdguesser.view.components.NerdGuesserScaffold
import com.example.nerdguesser.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    onSignOut: () -> Unit,
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    val signedIn by settingsViewModel.signedIn.collectAsStateWithLifecycle()
    if(!signedIn){
        onSignOut()
    }
    NerdGuesserScaffold(
        title = "Settings",
        onBackClick = { }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding),) {
            TextButton({}) {
                Text("More settings!")
            }
            TextButton({
                settingsViewModel.signOut()
            }) {
                Text("Sign out.")
            }
        }
    }
}