package com.example.nerdguesser.view.components

import android.app.appsearch.SearchResults
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.example.nerdguesser.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessBar(
    guess: String = "",
    onTextChange: (String) -> Unit,
    filterResults: List<String>
){
    val (allowExpanded, setExpanded) = remember { mutableStateOf(false) }
    val expanded = allowExpanded && filterResults.isNotEmpty()

    // We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = setExpanded,
    ) {
        OutlinedTextField(
            value = guess,
            onValueChange = {
                onTextChange(it)
                setExpanded(true)
            },
            label = null,
            placeholder = { Text(stringResource(R.string.guess_the_anime)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .menuAnchor(MenuAnchorType.PrimaryEditable)
        )

        ExposedDropdownMenu(
            modifier = Modifier.heightIn(max = 280.dp),
            expanded = expanded,
            onDismissRequest = { setExpanded(false) }
        ) {
            filterResults.forEach { option ->
                DropdownMenuItem(
                    text = {Text(option)},
                    onClick = {
                        onTextChange(option)
                        setExpanded(false)
                    }
                )
            }
        }
    }
}