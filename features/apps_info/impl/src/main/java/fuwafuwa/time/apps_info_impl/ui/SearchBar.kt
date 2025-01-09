package fuwafuwa.time.apps_info_impl.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fuwafuwa.time.core_compose.theme.GrayBlue
import kotlinx.coroutines.delay

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    debounceDuration: Long,
    onTextChanged: (string: String) -> Unit
) {
    var searchString by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(searchString) {
        if (searchString.isNotEmpty()) {
            delay(debounceDuration)
        }
        onTextChanged(searchString)
    }

    TextField(
        modifier = modifier,
        value = searchString,
        shape = RoundedCornerShape(8.dp),
        trailingIcon = {
            Icon(Icons.Filled.Search, "", tint = Color.White)
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = GrayBlue,
            unfocusedContainerColor = GrayBlue,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Color.White,
        ),
        singleLine = true,
        onValueChange = { newValue ->
            searchString = newValue
        }
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(
        debounceDuration = 500L
    ) {}
}
