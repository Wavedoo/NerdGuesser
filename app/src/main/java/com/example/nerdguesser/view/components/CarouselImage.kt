package com.example.nerdguesser.view.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nerdguesser.R
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration


@Composable
fun CarouselImage(@DrawableRes imageId: Int, title: String, contentDescription: String){
    Box (modifier = Modifier
        .width(LocalConfiguration.current.screenWidthDp.dp * 0.75f)){
        Image(
            modifier = Modifier.clip(MaterialTheme.shapes.extraLarge),
            painter = painterResource(id = imageId),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop
        )
        //TODO: Make better later.
        //ACtually i probably won't even use text on top of the image we'll see
        Text(
            text = title,
            modifier = Modifier.align(Alignment.BottomStart),
            fontSize = 30.sp,
            color = Color.Black,
            style = TextStyle(
                background = Color.Gray
            )

        )
    }

}

@Preview
@Composable
fun CarouselImagePreview(){
    CarouselImage(
        imageId = R.drawable.guess_anime_pic,
        title = "Guess The Anime",
        contentDescription = "Filler"
    )
}