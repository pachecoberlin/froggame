package de.pacheco.feature.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.pacheco.froggame.core.ui.FrogMainTheme

@Composable
fun CatchFrogScreen(modifier: Modifier = Modifier) {
    CatchFrogScreen(
        items = listOf("one", "two"),
        modifier = modifier
    )

}

@Composable
internal fun CatchFrogScreen(
    items: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        items.forEach {
            Text(it)
        }
    }
}

// Previews

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    FrogMainTheme {
        CatchFrogScreen(listOf("one", "two"))
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitPreview() {
    FrogMainTheme {
        CatchFrogScreen(listOf("one", "two"))
    }
}
