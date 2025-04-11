package com.example.nerdguesser.view.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.nerdguesser.R


//TODO: add more paramaters
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NerdGuesserScaffold(
    title: String,
    onBackClick: () -> Unit, /*Only for testing*/
    content: @Composable (PaddingValues) -> Unit
){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.inversePrimary,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    scrolledContainerColor = TopAppBarDefaults.topAppBarColors().scrolledContainerColor,
                    navigationIconContentColor = TopAppBarDefaults.topAppBarColors().navigationIconContentColor,
                    actionIconContentColor = TopAppBarDefaults.topAppBarColors().actionIconContentColor,
                    /*
                    TODO: Figure out why I don't need subtitle here
                    subtitleContentColor = TopAppBarDefaults.topAppBarColors().sub
                     */
                ),
                //TODO: Fix number thing
                title = { Text(title) },
                //TODO: Figure out best way (For this project) to pass iconbutton data to NerdGuesserScaffold. (i.e. IconButton, Icon and onClick, imageVector and onClick)
                navigationIcon = {
                    IconButton(onClick = {onBackClick()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = stringResource(R.string.back_arrow)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = stringResource(R.string.help_button)
                        )
                    }
                }
            )
        },
        content = content
    )
}