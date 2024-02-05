package de.pacheco.feature.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import de.pacheco.froggame.core.ui.DevicePreviews
import de.pacheco.froggame.core.ui.FrogMainTheme

@Composable
fun StartCatchFrogScreen(modifier: Modifier = Modifier, viewModel: CatchFrogViewModel = hiltViewModel()) {
    val modifier = modifier.padding(all = 30.dp ).fillMaxWidth()
    Column(modifier = modifier) {
        OutlinedTextField("Rows", modifier = modifier)
        OutlinedTextField("Columns", modifier = modifier)
        Button(onClick = startGame(), modifier = modifier) {
            Text(text = "Start Game")
        }
    }
}

fun startGame(): () -> Unit = {

}

@Composable
fun OutlinedTextField(label: String = "", modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        modifier = modifier
    )
}

@DevicePreviews
@Composable
private fun DefaultPreview() {
    FrogMainTheme {
        StartCatchFrogScreen()
    }
}