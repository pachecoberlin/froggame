package de.pacheco.froggame.feature.catchfrog.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import de.pacheco.froggame.core.ressources.R
import de.pacheco.froggame.core.ui.DevicePreviews
import de.pacheco.froggame.core.ui.FrogMainTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun CatchFrogScreen(
    modifier: Modifier = Modifier,
    rowsState: MutableIntState,
    colsState: MutableIntState,
    state: State<CatchFrogState>,
    caught: (Int) -> Unit,
    score: Int,
    replay: (Int, Int) -> Unit,
    highScore: Int,
    time: Int
) {
    val rows = rowsState.intValue
    val cols = colsState.intValue
    Column(modifier.verticalScroll(state = ScrollState(0))) {
        Row(modifier) {
            Text(text = "$time s", modifier = modifier)
            Column(Modifier.weight(1f)) {}
            Button(onClick = { replay(rows, cols) }, modifier = modifier.alpha(if (state.value is CatchFrogState.GameOver) 1f else 0f)) {
                Text(text = "play again")
            }
        }
        CatchFrogsMatrix(columns = cols, rows = rows, state, caught)
        Row(modifier = modifier.padding(bottom = 30.dp)) {
            Text(text = "Score: $score")
            Column(Modifier.weight(1f)) {}
            Text(text = "HighScore: $highScore", modifier = Modifier.padding(start = 10.dp))
        }
    }
}

@Composable
private fun CatchFrogsMatrix(columns: Int, rows: Int, state: State<CatchFrogState>, caught: (Int) -> Unit) {
    val frogId = if (state.value is CatchFrogState.Running) (state.value as CatchFrogState.Running).frogIsShowing else -1
    val column = frogId / rows
    val row = frogId % rows
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Column(Modifier.weight(1f)) {}
        for (i in 0..<columns) {
            Column {
                CatchableFrogsRows(rows, if (i == column) row else -1, caught, frogId)
            }
            Column(Modifier.weight(1f)) {}
        }
    }
}

@Composable
private fun CatchableFrogsRows(rows: Int, showing: Int, caught: (Int) -> Unit, frogId: Int) {
    for (i in 0..<rows) {
        Row {
            CatchableFrog(i == showing, caught, frogId)
        }
    }
}

@Composable
fun CatchableFrog(isVisible: Boolean, caught: (Int) -> Unit, i: Int) {
    Button(
        onClick = caught(caught, i),
        border = null,
        enabled = isVisible,
        elevation = null,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, disabledContainerColor = Color.Transparent),
    ) {
        Image(modifier = Modifier.alpha(if (isVisible) 1f else 0f), painter = painterResource(id = R.drawable.frog), contentDescription = "a frog to catch")
    }
}

private fun caught(caught: (Int) -> Unit, i: Int): () -> Unit {
    return { caught(i) }
}

@SuppressLint("UnrememberedMutableState")
@DevicePreviews
@Composable
private fun DefaultPreview() {
    val score = 345
    val time = 3
    val state = MutableStateFlow(CatchFrogState.Running(8)).asStateFlow().collectAsState()
    val rowState = mutableIntStateOf(6)
    val colState = mutableIntStateOf(4)
    FrogMainTheme {
        CatchFrogScreen(rowsState = rowState, colsState = colState, state = state, caught = { }, score = score, replay = { _, _ -> }, highScore = score, time = time)
    }
}
