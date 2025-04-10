package com.example.nerdguesser.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nerdguesser.R

//TODO: Fix and get image from  server at some point
@Composable
fun FrameImage(imageId: Int, contentDescription: String){
    Image(
        painter = painterResource(imageId),
        contentDescription = contentDescription,
        modifier = Modifier.aspectRatio(16f / 9f)
    )
}

@Preview
@Composable
fun PreviewFrameImage(){
    Column(modifier = Modifier.fillMaxWidth()) {
        FrameImage(R.drawable.frieren_village_1, "test")
        FrameImage(R.drawable.fern_stark_5, "test")
        FrameImage(R.drawable.ubel_4, "test")
        FrameImage(R.drawable.frieren_landscape_6, "test")
    }
}