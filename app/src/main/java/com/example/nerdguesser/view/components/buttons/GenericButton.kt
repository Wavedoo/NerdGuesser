package com.example.nerdguesser.view.components.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.nerdguesser.R

@Composable
fun GenericButton(text: String, onClick: () -> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier.height(40.dp).width(140.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Text(text)
    }
}