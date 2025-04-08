package com.example.nerdguesser.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nerdguesser.R
import com.example.nerdguesser.ui.theme.NerdGuesserTheme
import com.example.nerdguesser.view.components.FrameBar
import com.example.nerdguesser.view.components.GameOverSection
import com.example.nerdguesser.view.components.GuessSection
import com.example.nerdguesser.view.components.HintsSection
import com.example.nerdguesser.viewmodel.GuessingGameViewModel

private const val s = "Help button"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessAnimeScreen(gameViewModel: GuessingGameViewModel = viewModel()){
    val gameUiState by gameViewModel.uiState.collectAsState()

    var currentFrame: Int by remember { mutableIntStateOf(1) }
    //var remainingGuesses: Int by remember { mutableIntStateOf(6) }
    //var guess: String by remember { mutableStateOf("")}
    //val hints = Hints()
    //var guesses: List<String> by remember { mutableStateListOf<String>() }
    //val answer: String = "Frieren"
    NerdGuesserTheme{
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.inversePrimary,
                        titleContentColor = MaterialTheme.colorScheme.onBackground,
                        scrolledContainerColor = TopAppBarDefaults.topAppBarColors().scrolledContainerColor,
                        navigationIconContentColor = TopAppBarDefaults.topAppBarColors().navigationIconContentColor,
                        actionIconContentColor = TopAppBarDefaults.topAppBarColors().actionIconContentColor,
                        /*
                        TODO: Figure out why I don't need subtitle here
                        subtitleContentColor = TopAppBarDefaults.topAppBarColors().sub
                         */
                    ),
                    //TODO: Fix number thing
                    title = {Text(stringResource(R.string.anime_number, 1))},
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = stringResource(R.string.back_arrow)
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Rounded.Search,
                                contentDescription = stringResource(R.string.help_button)
                            )
                        }
                    }
                )
            }
        ) {
            innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ){
                //Text("hi")
                Image(
                    painter = painterResource(gameUiState.images[gameUiState.currentFrame-1]),
                    contentDescription = "Frieren"
                )
                //TODO: Card?
                FrameBar(
                    currentFrame = gameUiState.currentFrame,
                    remainingGuesses = gameUiState.remainingGuesses,
                    onFrameChange = {gameViewModel.updateFrame(it)}
                )
                if(gameUiState.isGameOver){
                    GameOverSection(
                        correct = gameUiState.isCorrect,
                        /*answer = gameUiState.correctAnswer, */
                        guesses = gameUiState.guesses,
                        hints = gameUiState.hints
                    )
                }else{
                    GuessSection(
                        guess = gameViewModel.userGuess,
                        remainingGuesses = gameUiState.remainingGuesses,
                        onTextChange = {gameViewModel.updateGuess(it)},
                        onSubmit = { gameViewModel.checkUserGuess() }
                    )
                }
                HintsSection(gameUiState.hints, gameUiState.hintsShown)

            }
        }
    }
}

@Preview
@Composable
fun PreviewScreen(){
    GuessAnimeScreen()
}