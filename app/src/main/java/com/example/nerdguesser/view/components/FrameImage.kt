package com.example.nerdguesser.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

//TODO: Fix and get image from  server at some point
@Composable
fun FrameImage(imageId: Int, contentDescription: String){
    Image(
        painter = painterResource(imageId),
        contentDescription = contentDescription,
        modifier = Modifier/*.fillMaxWidth().height(210.dp)*/
    )
}