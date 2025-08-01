package com.example.nerdguesser.view.components.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GenericButton(
    modifier:Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    height: Dp = 40.dp,
    width: Dp = 140.dp
){
    val buttonModifier = if(modifier == Modifier){
        Modifier.height(height).width(width)
    }else{
        modifier
    }

    Button(
        onClick = onClick,
        modifier = buttonModifier,
        shape = MaterialTheme.shapes.small
    ) {

        Text(text)
    }
}