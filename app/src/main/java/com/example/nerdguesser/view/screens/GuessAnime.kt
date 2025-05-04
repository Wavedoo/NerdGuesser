package com.example.nerdguesser.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
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
fun GuessAnimeScreen(id: String, gameViewModel: GuessingGameViewModel = hiltViewModel()){
    gameViewModel.tempInit(id)

    val gameUiState by gameViewModel.uiState.collectAsState()
    val clipboardManager = LocalClipboardManager.current

    //TODO: Add documentations and more comments? - Not needed
    //TODO: Refactor code, universal button, theming  - Take a second look at the universal button

    //TODO: Find a good colour scheme and see if dynamic colour is good - No dynamic colour, custom colour theme is low priority
    //TODO: Proper theming colours, typography, shapes, etc - I am now using Material3 stuff
    //TODO: ModalNavigationDrawer is probably good or i could just use home -> anime -> anime #1 and back buttons - expanding the app should be my current priority


    //TODO: https://firebase.google.com/codelabs/firestore-android#0
    //TODO: https://developers.google.com/learn/pathways/firebase-android-jetpack
    //TODO: https://firebase.google.com/codelabs/build-android-app-with-firebase-compose#0
    //https://www.reddit.com/r/Firebase/comments/k4lj94/connecting_firebase_emulator_suite_with_real/
    //TODO: Service implementations

    //TODO: Learn navigation https://developer.android.com/guide/navigation/principles

    //TODO: Figure out why removing nerdguessertheme breaks this
    NerdGuesserTheme(dynamicColor = false){
        NerdGuesserScaffold(
            title = stringResource(R.string.anime_number, gameUiState.gameData.day),
            //title = gameData.name,
            onBackClick = {/* gameViewModel.getAnswerDetails()*/ }
        ) {
            innerPadding ->
            if(gameUiState.images.size != 6){
                LoadingIndicator(innerPadding)
            }else{
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ){
                    //Text("hi")
                    if(gameUiState.images.size > 0){
                        FrameImage(
                            imageBitmap = gameUiState.images[gameUiState.imageIndex],
                        )
                    }

                    //TODO: Card?
                    FrameBar(
                        currentFrame = gameUiState.currentFrame,
                        frameStatuses = gameUiState.guessResults,
                        onFrameChange = {gameViewModel.updateFrame(it)}
                    )
                    if(gameUiState.isGameOver){
                        GameOverSection(
                            correct = gameUiState.isCorrect,
                            guesses = gameUiState.guesses,
                            hints = gameUiState.gameData.hints,
                            onShareClick = {clipboardManager.setText(gameViewModel.shareResults("Anime"))}
                        )
                    }else{
                        GuessSection(
                            guess = gameViewModel.userGuess,
                            remainingGuesses = gameUiState.remainingGuesses,
                            onTextChange = {gameViewModel.updateGuess(it)},
                            onSubmit = { gameViewModel.checkUserGuess() }
                        )
                        HintsSection(gameUiState.gameData.hints, gameUiState.hintsShown)
                    }

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