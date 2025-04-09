package com.example.nerdguesser.view.components.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShareButton(){
    Button(
        onClick = {},
        modifier = Modifier
            .height(40.dp)
            .width(140.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text("Share results")
    }
}