package com.example.nerdguesser.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nerdguesser.view.components.CarouselGames
import com.example.nerdguesser.view.components.NerdGuesserScaffold
import com.example.nerdguesser.view.components.buttons.GenericButton
import com.example.nerdguesser.viewmodel.HomeViewModel

//TODO: Carousel with play daily and play all options
//Settings button is here in actions
@Composable
fun HomeScreen(
    navigateToGames: () -> Unit,
    navigateToSettings: () -> Unit,
){
    //TODO: Carousel
    NerdGuesserScaffold(
        title = "Nerd Guesser",
        onBackClick = {},
        actionIconButton = {
            IconButton(onClick = navigateToSettings) {
                Icon(
                    imageVector = Icons.Rounded.Settings,
                    contentDescription = "Settings"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CarouselGames()
            Text("Stats go here.")
        }
    }
}