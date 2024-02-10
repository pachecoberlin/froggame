package de.pacheco.froggame.feature.catchfrog.ui

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.pacheco.froggame.core.ressources.R.string.catchFrogs
import de.pacheco.froggame.core.ui.DevicePreviews
import de.pacheco.froggame.core.ui.FrogMainTheme

@Composable
fun StartCatchFrogScreen(modifier: Modifier = Modifier, viewModel: CatchFrogViewModel = hiltViewModel(), navigateTo: (String) -> Unit) {
    val gameState = viewModel.gameState.collectAsStateWithLifecycle()
    println(gameState.value)
    when (gameState.value) {
        CatchFrogState.Preparation -> {
            StartCatchFrogScreenInternal(modifier = modifier, functions = viewModel.functions, navigateTo = navigateTo)
        }

        else -> {
            CatchFrogScreen(modifier = modifier, viewModel.rows to viewModel.cols, gameState)
        }
    }

}

@SuppressLint("UnrememberedMutableState")
@Composable
internal fun StartCatchFrogScreenInternal(
    modifier: Modifier = Modifier, functions: Map<Function, (params: Map<Parameter, Any>) -> Unit> = emptyMap(), navigateTo: (String) ->
    Unit
) {
    val modifiedModifier = modifier
        .padding(all = 30.dp)
        .fillMaxWidth()
    val rows: MutableIntState = mutableIntStateOf(6)
    val cols: MutableIntState = mutableIntStateOf(6)
    val catchFrogs = stringResource(id = catchFrogs)
    Column(modifier = modifiedModifier) {
        OutlinedTextField(modifier = modifiedModifier, label = stringResource(Parameter.ROWS.text), rows)
        OutlinedTextField(modifier = modifiedModifier, label = stringResource(Parameter.COLS.text), cols)
        Button(modifier = modifiedModifier, onClick = startGame(functions[Function.STARTGAME], rows, cols, navigateTo, catchFrogs)) {
            //TODO darktheme text color to bright
            Text(text = stringResource(Function.STARTGAME.text))
        }
    }
}

/**
 * TODO fix or delete would be used in StartCatchFrogScreenInternal and passed to startGame
 * This leads to CatchFrogScreen is shown on top of StartCatchFrogScreen
 * It should be implemented in that way that each feature has its own function which returns a composable. Need to figure out how NIA handles the route.
 */
//@Composable
//private fun extendNavigation(): () -> Unit {
//    val navController = rememberNavController()
//    val catchFrogs = stringResource(id = catchFrogs)
//    val startCatchFrogs = stringResource(id = startCatchFrogs)
//    NavHost(navController = navController, startDestination = startCatchFrogs) {
//        navigation(startDestination = "anotherName", route = startCatchFrogs) {
//            composable(route = "anotherName") { /*This is empty so the startCatchFrogsScreen is shown and not build with a loop*/ }
//            composable(route = catchFrogs) { CatchFrogScreen(modifier = Modifier.padding(20.dp)) }
//        }
//    }
//    return { navController.navigate(catchFrogs) }
//}

fun startGame(function: ((params: Map<Parameter, Any>) -> Unit)?, rows: MutableIntState, cols: MutableIntState, navigateTo: (String) -> Unit, catchFrogs: String): () -> Unit = {
    function?.invoke(mapOf(Parameter.ROWS to rows.intValue, Parameter.COLS to cols.intValue))
//    navigateTo(catchFrogs)
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
        StartCatchFrogScreenInternal(navigateTo = { })
    }
}