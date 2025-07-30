package com.example.nerdguesser.view.screens

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Help
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.nerdguesser.R
import com.example.nerdguesser.model.classes.GameData
import com.example.nerdguesser.ui.theme.NerdGuesserTheme
import com.example.nerdguesser.view.components.FrameBar
import com.example.nerdguesser.view.components.FrameImage
import com.example.nerdguesser.view.components.GameOverSection
import com.example.nerdguesser.view.components.GuessSection
import com.example.nerdguesser.view.components.HintsSection
import com.example.nerdguesser.view.components.LoadingIndicator
import com.example.nerdguesser.view.components.NerdGuesserScaffold
import com.example.nerdguesser.viewmodel.GuessingGameViewModel

private const val s = "Help button"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessAnimeScreen(
    navController: NavController,
    id: String,
    gameViewModel: GuessingGameViewModel = hiltViewModel()){
    LaunchedEffect(true) {
        gameViewModel.tempInit(id)
    }

    val gameUiState by gameViewModel.uiState.collectAsStateWithLifecycle()
    val clipboard = LocalClipboard.current

    //TODO: Add documentations and more comments? - Not needed
    //TODO: Take another look at how I have everything set up - Components and buttons and stuff
    //TODO: Get better at using Material3

    val title = if (gameUiState.gameData.day != 0) stringResource(R.string.anime_number, gameUiState.gameData.day) else "Anime Guesser"
    NerdGuesserScaffold(
        title = title,
        onBackClick = {navController.popBackStack()},
        actionIconButton = {
            IconButton(onClick = {/*TODO: Add a help modal*/}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.Help,
                    contentDescription = stringResource(R.string.help_button)
                )
            }
        }
    ) {
        innerPadding ->
        //The game is ready to be played
        if(gameUiState.images.size != 6){
            LoadingIndicator(innerPadding)
        }else{
            //TODO: Possibly move to GuessAnimeScreenContent composable
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ){
                FrameImage(
                    imageBitmap = gameUiState.images[gameUiState.currentFrame-1],
                )

                FrameBar(
                    currentFrame = gameUiState.currentFrame,
                    frameStatuses = gameUiState.guessResults,
                    onFrameChange = {gameViewModel.updateFrame(it)}
                )
                if(gameUiState.isGameOver){
                    val context = LocalContext.current
                    GameOverSection(
                        correct = gameUiState.isCorrect,
                        answer = gameUiState.gameData.name,
                        guesses = gameUiState.guesses,
                        hints = gameUiState.gameData.hints,
                        //TODO: Fix this
                        onShareClick = {
                            val shareIntent = Intent.createChooser(gameViewModel.getResultsIntent("Anime"), null)
                            context.startActivity(shareIntent)
                        }
                    )
                }else{
                    GuessSection(
                        guess = gameViewModel.userGuess,
                        remainingGuesses = gameUiState.remainingGuesses,
                        onTextChange = {gameViewModel.updateGuess(it)},
                        onSubmit = { gameViewModel.checkUserGuess() },
                        filteredResults = gameUiState.filteredResults
                    )
                    HintsSection(gameUiState.gameData.hints, gameUiState.hintsShown)
                }

            }
        }

    }

}

@Preview
@Composable
fun PreviewScreen(){
    //GuessAnimeScreen()
}