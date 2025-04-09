package com.example.nerdguesser.view.components.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//Hex is ARGB values
enum class Status(val rgb: Long){
    NotGuessed(0xFF7D7D7D),
    Wrong(0xFFDC3D3D),
    Correct(0xFF3DDC85),
    Close(0xFFCFCC53),
    Disabled(0xFFD9D9D9)
}


//TODO: Add functionality

@Preview
@Composable
fun FrameButton(
    modifier: Modifier = Modifier
        .width(50.dp)
        .height(50.dp),
    number: Int = 1,
    status: Status = Status.NotGuessed,
    selected: Boolean = false,
    onFrameChange:  (Int) -> Unit = {},
    enabled: Boolean = false
){
    Button (
        onClick = {onFrameChange(number)},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(status.rgb),
            disabledContainerColor = Color(Status.Disabled.rgb)
        ),
        shape = RoundedCornerShape(10.dp),
        enabled = enabled,
        modifier = modifier
    ){
        val weight: FontWeight = if(selected) FontWeight.Bold else FontWeight.Normal
        Text(
            text = number.toString(),
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontWeight = weight
        )
    }
}