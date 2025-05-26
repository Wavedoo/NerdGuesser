package com.example.nerdguesser.view.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordOutlinedTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    supportingText: String = "",
    imeAction: ImeAction = ImeAction.Done
){
    //TODO: Change remembers to remembersaveable?
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    OutlinedTextField(
        modifier = modifier,
        value = value,
        label = { Text(label) },
        onValueChange = onValueChange,
        visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            //TODO: Maybe change this to something that logs in when pressed?
            imeAction = imeAction
        ),
        trailingIcon = {
            IconButton(onClick = {passwordHidden = !passwordHidden}){
                Icon(
                    imageVector = if(passwordHidden) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff,
                    contentDescription = "show or hide password"
                )
            }
        },
        isError = isError,
        supportingText = { Text(supportingText) }
    )
}
