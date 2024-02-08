package de.pacheco.froggame.feature.catchfrog.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.pacheco.froggame.core.ressources.R
import de.pacheco.froggame.core.ui.DevicePreviews
import de.pacheco.froggame.core.ui.FrogMainTheme

@Composable
fun CatchFrogScreen(modifier: Modifier = Modifier, viewModel: CatchFrogViewModel = hiltViewModel()) {
    val catchFrogState by viewModel.gameState.collectAsStateWithLifecycle()
    catchFrogState.let { println(it) }
    CatchFrogScreen(
        6 to 6, //TODO get amount of frogs or rows and columns better the amount of frogs. then also change startCatchFrogScreen
        modifier = modifier,
        state = catchFrogState
    )
}

@Composable
internal fun CatchFrogScreen(
    size: Pair<Int, Int>,
    modifier: Modifier = Modifier,
    state: CatchFrogState
) {
    Column(modifier.verticalScroll(state = ScrollState(0))) {
        Row(modifier) {
            Text(text = "Time", modifier = modifier)
            Column(Modifier.weight(1f)) {}
            Button(onClick = { replay() }, modifier = modifier) {
                Text(text = "play again")
            }
        }
        CatchFrogsMatrix(columns = size.first, rows = size.second, state)
        Row(modifier = modifier.padding(bottom = 30.dp)) {
            Text(text = "Score: 0")
            Column(Modifier.weight(1f)) {}
            Text(text = "HighScore: 0", modifier = Modifier.padding(start = 10.dp))
        }
    }
}

@Composable
private fun CatchFrogsMatrix(columns: Int, rows: Int, state: CatchFrogState) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        for (i in 0..<columns) {
            Column {
                CatchableFrogsRows(rows)
            }
            Column(Modifier.weight(1f)) {}
        }
    }
}

@Composable
private fun CatchableFrogsRows(rows: Int) {
    for (i in 0..<rows) {
        Row {
            CatchableFrog()
        }
    }
}

@Composable
fun CatchableFrog() {
    Image(modifier = Modifier.alpha(0f), painter = painterResource(id = R.drawable.frog), contentDescription = "a frog to catch")
}

private fun replay() {}

@DevicePreviews
@Composable
private fun DefaultPreview() {
    FrogMainTheme {
        CatchFrogScreen(size = 6 to 6, state = CatchFrogState.Running(0))
    }
}
