package de.pacheco.feature.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import de.pacheco.froggame.core.ui.DevicePreviews
import de.pacheco.froggame.core.ui.FrogMainTheme

@Composable
fun StartCatchFrogScreen(modifier: Modifier = Modifier, viewModel: CatchFrogViewModel = hiltViewModel()) {
    StartCatchFrogScreenInternal(modifier = modifier, functions = viewModel.functions)
}

@SuppressLint("UnrememberedMutableState")
@Composable
internal fun StartCatchFrogScreenInternal(modifier: Modifier = Modifier, functions: Map<Function, (params: Map<Parameter,Any>) -> Unit> = emptyMap()) {
    val modifiedModifier = modifier
        .padding(all = 30.dp)
        .fillMaxWidth()
    val rows: MutableIntState = mutableIntStateOf(6)
    val cols: MutableIntState = mutableIntStateOf(6)
    Column(modifier = modifiedModifier) {
        OutlinedTextField(modifier = modifiedModifier, label = Parameter.ROWS.text, rows)
        OutlinedTextField(modifier = modifiedModifier, label = Parameter.COLS.text, cols)
        Button(modifier = modifiedModifier, onClick = startGame(functions[Function.STARTGAME], rows, cols)) {
            //TODO darktheme text color to bright
            Text(text = Function.STARTGAME.text)
        }
    }
}

fun startGame(function: ((params: Map<Parameter,Any>) -> Unit)?, rows: MutableIntState, cols: MutableIntState): () -> Unit = {
    function?.invoke(mapOf(Parameter.ROWS to rows, Parameter.COLS to cols))
}

@Composable
fun OutlinedTextField(modifier: Modifier = Modifier, label: String = "", mutableIntState: MutableIntState) {
    var number by rememberSaveable { mutableIntState }

    OutlinedTextField(
        value = number.toString(),
        onValueChange = { number = it.toIntOrNull() ?: number },
        label = { Text(label) },
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}

@DevicePreviews
@Composable
private fun DefaultPreview() {
    FrogMainTheme {
        StartCatchFrogScreenInternal()
    }
}