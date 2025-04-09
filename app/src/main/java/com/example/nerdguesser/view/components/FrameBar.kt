package com.example.nerdguesser.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nerdguesser.view.components.buttons.FrameButton
import com.example.nerdguesser.view.components.buttons.Status


//TODO: Keep track of status in array in parent caller
@Preview
@Composable
fun FrameBar(currentFrame: Int = 1, remainingGuesses: Int = 6, onFrameChange: (Int) -> Unit = {}) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        for(i: Int in 1..6){
            val enabled = if (i <= 7 - remainingGuesses) true else false
            FrameButton(
                number = i,
                status = Status.NotGuessed,
                selected = currentFrame == i,
                onFrameChange = onFrameChange,
                enabled = enabled
            )
        }
    }

}