package com.example.nerdguesser.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun NerdLoadingIndicator(padding: PaddingValues){
    Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center){
        LoadingIndicator(
            modifier = Modifier.scale(2f)
        )
    }
}

@Preview
@Composable
fun PreviewIndicator(){
    NerdLoadingIndicator(padding = PaddingValues())
}