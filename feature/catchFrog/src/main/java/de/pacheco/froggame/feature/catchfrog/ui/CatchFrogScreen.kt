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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
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
fun CatchFrogScreen(modifier: Modifier = Modifier, size: Pair<Int, Int>, state: State<CatchFrogState>, caught: (Int) -> Unit) {
    Column(modifier.verticalScroll(state = ScrollState(0))) {
        Row(modifier) {
            Text(text = "Time", modifier = modifier)
            Column(Modifier.weight(1f)) {}
            Button(onClick = { replay() }, modifier = modifier) {
                Text(text = "play again")
            }
        }
        CatchFrogsMatrix(columns = size.first, rows = size.second, state, caught)
        Row(modifier = modifier.padding(bottom = 30.dp)) {
            Text(text = "Score: 0")
            Column(Modifier.weight(1f)) {}
            Text(text = "HighScore: 0", modifier = Modifier.padding(start = 10.dp))
        }
    }
}

@Composable
private fun CatchFrogsMatrix(columns: Int, rows: Int, state: State<CatchFrogState>, caught: (Int) -> Unit) {
    val showNumber = if (state.value is CatchFrogState.Running) (state.value as CatchFrogState.Running).frogIsShowing / columns else -1
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        for (i in 0..<columns) {
            Column {
                CatchableFrogsRows(rows, if (i == showNumber) showNumber else -1, caught)
            }
            Column(Modifier.weight(1f)) {}
        }
    }
}

@Composable
private fun CatchableFrogsRows(rows: Int, showing: Int, caught: (Int) -> Unit) {
    for (i in 0..<rows) {
        Row {
            CatchableFrog(i == showing, caught, i)
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

private fun replay() {
    //TODO
}

@DevicePreviews
@Composable
private fun DefaultPreview() {
    FrogMainTheme {
        CatchFrogScreen(size = 6 to 6, state = MutableStateFlow(CatchFrogState.Running(2)).asStateFlow().collectAsState(), caught = { })
    }
}
