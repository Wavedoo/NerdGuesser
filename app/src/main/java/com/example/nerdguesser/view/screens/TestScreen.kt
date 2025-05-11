package com.example.nerdguesser.view.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.toMutableStateList

//TODO: OutlinedSecureTextField fopr passwodrs
//TODO: try searchbar, compare the results?

val options = listOf(
    "Naruto",
    "Naruto Shippuden",
    "Frieren",
    "Spy x Family",
    "Neon Genesis Evangelion",
    "86",
    "Rock Is a Lady's Modesty",
    "Attack on Titan",
    "Shingeki no Kyojin"
)

//Filter the word
fun testFilter(word: String): MutableList<String>{
    //starts the list with words that start with the word
    val tempList: MutableList<String> = options.filter { it.startsWith(word, ignoreCase = true) }.toMutableList()
    //adds to the list words that contain the word but don't start with the word
    tempList.addAll(options.filter { !it.startsWith(word, ignoreCase = true) && it.contains(word, ignoreCase = true) })
    return tempList
}
//Use exposedropdown, figure out filtering algorithm
//Use filtering on list of 10,000 animes cause it's cheaper
//Learn best way to put best results at top of the list
@Composable
fun TestScreen(){
    //Stateflow list
    var results = remember { options.toMutableStateList() }
    val filteredList = remember { options.toMutableStateList() }
    val textFieldState = rememberTextFieldState("")
    Column(modifier = Modifier.fillMaxSize().background(Color.White)){
        EditableExposedDropdownMenuSample()
        /*SearchBarBasic(
            textFieldState = textFieldState,
            results = filteredList
        ){ word ->
            //Log.d("Anime", "Searching in $results")
            //Log.d("Anime", "Filtered: " + options.filter { it.contains(word, ignoreCase = true) }.toMutableStateList())
            //results = options.filter { it.contains(word, ignoreCase = true) }.toMutableStateList()
            //filteredList.clear()
            //filteredList.addAll(options.filter { it.contains(word, ignoreCase = true)  })
            //results.filter { it.contains(word, ignoreCase = true)  }
            filteredList.clear()
            filteredList.addAll(testFilter(word))
            Log.d("Anime", filteredList.toString())
            //results.removeAt(results.size - 1)
        }*/
        //EditableExposedDropdownMenuSample()
        //ExposedDropdownMenuSample()
        //Text("hi")
        //SearchBarTest()

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarBasic(
    textFieldState: TextFieldState,
    results: List<String>,
    onQueryChange: (String) -> Unit
){
    var expanded by rememberSaveable { mutableStateOf(false) }
    //val textFieldState = rememberTextFieldState("")

    Box(modifier = Modifier.fillMaxSize()){
        SearchBar(
            modifier = Modifier.align(Alignment.TopCenter),
            inputField = {
                SearchBarDefaults.InputField(
                    query = textFieldState.text.toString(),
                    onQueryChange = {
                        textFieldState.edit { replace(0, length, it) }
                        onQueryChange(textFieldState.text.toString())
                    },
                    onSearch = {
                        Log.d("Anime", "onSearch called?")
                        expanded = false
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text("Search") }
                )
            },
            expanded = expanded,
            onExpandedChange = {expanded = it}
        ) {
            ListItems(textFieldState = textFieldState, results = results) { expanded = false }
        }
    }

}
@Composable
fun ListItems(textFieldState: TextFieldState, results: List<String>, updateExpanded: () -> Unit){
    // Display search results in a scrollable column
    Column(Modifier.verticalScroll(rememberScrollState())) {
        results.forEach { result ->
            ListItem(
                headlineContent = { Text(result) },
                modifier = Modifier
                    .clickable {
                        textFieldState.edit { replace(0, length, result) }
                        updateExpanded()
                    }
                    .fillMaxWidth()
            )
        }
    }
}

/*@Composable
fun ListItemsLazy(){
    // Show search results in a lazy column for better performance
    LazyColumn {
        items(count = searchResults.size) { index ->
            val resultText = searchResults[index]
            ListItem(
                headlineContent = { Text(resultText) },
                supportingContent = supportingContent?.let { { it(resultText) } },
                leadingContent = leadingContent,
                colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                modifier = Modifier
                    .clickable {
                        onResultClick(resultText)
                        expanded = false
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }
    }
}*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarTestOld(){
    var expanded by remember { mutableStateOf(false) }
    var guess by remember { mutableStateOf(options[0]) }
    val textFieldState = rememberTextFieldState(options[0])
// We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        /*OutlinedTextField(
            readOnly = true,
            state = textFieldState,
            label = Text("Label"),
            trailingIcon = ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded),
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )*/
        /*TextField(
            value = guess,
            onValueChange = { guess = options[2] },
            //placeholder = {Text(stringResource(R.string.guess_the_anime))},
            trailingIcon = Icon(Icons.Rounded.Search, contentDescription = "Test"),
            modifier =  Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )*/
        val filters = options.filter { it.contains(guess, ignoreCase = true) }
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {Text(selectionOption)},
                    onClick = {}

                )
                /*DropdownMenuItem(
                    onClick = {
                        textFieldState.setTextAndPlaceCursorAtEnd(selectionOption)
                        expanded = false
                    }
                ) {
                    Text(text = selectionOption)
                }*/

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuSample() {
    var expanded by remember { mutableStateOf(false) }
    var guess by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        OutlinedTextField(
            // The `menuAnchor` modifier must be passed to the text field to handle
            // expanding/collapsing the menu on click. A read-only text field has
            // the anchor type `PrimaryNotEditable`.
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable),
            onValueChange = {},
            value = guess,
            readOnly = true,
            label = {Text("Label")},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded,
                    // If the text field is editable, it is recommended to make the
                    // trailing icon a `menuAnchor` of type `SecondaryEditable`. This
                    // provides a better experience for certain accessibility services
                    // to choose a menu option without typing.
                    modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option, style = MaterialTheme.typography.bodyLarge) },
                    onClick = {
                        guess = option
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}


/*
Downside: It's not ordered by most relevant
*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableExposedDropdownMenuSample() {
    val textFieldState = rememberTextFieldState()

    // The text that the user inputs into the text field can be used to filter the options.
    var guess by remember { mutableStateOf("") }
    // This sample uses string subsequence matching.
    //val filters = options.filter { it.contains(guess, ignoreCase = true) }
    val filters = testFilter(word = guess)

    val (allowExpanded, setExpanded) = remember { mutableStateOf(false) }
    //TODO: Do I need this?!
    //Yes I do otherwise it shows an ugly empty list
    val expanded = allowExpanded && filters.isNotEmpty()

    //Do I need setExpanded
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = setExpanded,
    ) {
        TextField(
            // The `menuAnchor` modifier must be passed to the text field to handle
            // expanding/collapsing the menu on click. An editable text field has
            // the anchor type `PrimaryEditable`.
            value = guess,
            onValueChange = {
                guess =it
                setExpanded(true)
            },
            label = { Text("Label") },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded,
                    // If the text field is editable, it is recommended to make the
                    // trailing icon a `menuAnchor` of type `SecondaryEditable`. This
                    // provides a better experience for certain accessibility services
                    // to choose a menu option without typing.
                    modifier = Modifier.menuAnchor(MenuAnchorType.SecondaryEditable),
                )
            },
        )
        ExposedDropdownMenu(
            modifier = Modifier.heightIn(max = 280.dp),
            expanded = expanded,
            onDismissRequest = { setExpanded(false) },
        ) {
            filters.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option, style = MaterialTheme.typography.bodyLarge) },
                    onClick = {
                        //textFieldState.setTextAndPlaceCursorAtEnd(option.text)
                        guess = option
                        setExpanded(false)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@Preview
@Composable
fun TestScreenPreview(){
    TestScreen()
}