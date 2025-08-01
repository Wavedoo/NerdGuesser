package com.example.nerdguesser.view.components.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nerdguesser.view.navigation.ScreenRoute

@Composable
fun NavigationButton(
    modifier: Modifier = Modifier,
    text: String,
    navController: NavController,
    route: ScreenRoute,
){
    GenericButton(
        onClick = { navController.navigate(route) },
        modifier = modifier,
        text = text,
    )
}