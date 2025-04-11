package com.example.nerdguesser.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nerdguesser.R
import com.example.nerdguesser.ui.theme.NerdGuesserTheme
import com.example.nerdguesser.view.components.FrameBar
import com.example.nerdguesser.view.components.FrameImage
import com.example.nerdguesser.view.components.GameOverSection
import com.example.nerdguesser.view.components.GuessSection
import com.example.nerdguesser.view.components.HintsSection
import com.example.nerdguesser.viewmodel.GuessingGameViewModel

private const val s = "Help button"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessAnimeScreen(gameViewModel: GuessingGameViewModel = viewModel()){
    val gameUiState by gameViewModel.uiState.collectAsState()
    val clipboardManager = LocalClipboardManager.current

    //TODO: Add documentations and more comments?
    //TODO: Refactor code, universal button, theming
    //TODO: Extract modifiers?

    //TODO: Find a good colour scheme and see if dynamic colour is good
    //TODO: Proper theming colours, typography, shapes, etc
    //TODO: ModalNavigationDrawer is probably good or i could just use home -> anime -> anime #1 and back buttons
    NerdGuesserTheme(dynamicColor = false){
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
                        IconButton(onClick = {gameViewModel.getAnswerDetails()}) {
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
                modifier = Modifier.padding(innerPadding).verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ){
                //Text("hi")

                FrameImage(
                    imageId = gameUiState.currentImage,
                    contentDescription = "Frieren"
                )
                //TODO: Card?
                FrameBar(
                    currentFrame = gameUiState.currentFrame,
                    frameStatuses = gameUiState.guessResults,
                    onFrameChange = {gameViewModel.updateFrame(it)}
                )
                if(gameUiState.isGameOver){
                    GameOverSection(
                        correct = gameUiState.isCorrect,
                        /*answer = gameUiState.correctAnswer, */
                        guesses = gameUiState.guesses,
                        hints = gameUiState.hints,
                        onShareClick = {clipboardManager.setText(gameViewModel.shareResults("Anime"))}
                    )
                }else{
                    GuessSection(
                        guess = gameViewModel.userGuess,
                        remainingGuesses = gameUiState.remainingGuesses,
                        onTextChange = {gameViewModel.updateGuess(it)},
                        onSubmit = { gameViewModel.checkUserGuess() }
                    )
                    HintsSection(gameUiState.hints, gameUiState.hintsShown)
                }

            }
        }
    }
}

@Preview
@Composable
fun PreviewScreen(){
    GuessAnimeScreen()
}