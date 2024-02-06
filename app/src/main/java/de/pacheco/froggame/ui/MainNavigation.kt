package de.pacheco.froggame.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.pacheco.feature.ui.CatchFrogScreen
import de.pacheco.feature.ui.StartCatchFrogScreen
import de.pacheco.froggame.R.string.catchFrogs
import de.pacheco.froggame.R.string.main
import de.pacheco.froggame.R.string.startCatchFrogs
import de.pacheco.froggame.feature.frogdata.ui.FrogDataScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.semantics {
//            testTagsAsResourceId = true
        },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
//        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            MainBottomBar(
                modifier = Modifier.testTag("FrogBottomBar"),
                navController = navController
            )
        },
    ) { padding ->
        Row(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
        ) {
            val catchFrogs = stringResource(catchFrogs)
            val main = stringResource(main)
            val startCatchFrogs = stringResource(startCatchFrogs)
            // TODO: We may want to add padding or spacer when the snackbar is shown so that
            //  content doesn't display behind it.
            NavHost(navController = navController, startDestination = main) {
                composable(main) { FrogDataScreen(modifier = Modifier.padding(16.dp)) }
                composable(route = startCatchFrogs) { StartCatchFrogScreen(modifier = Modifier.padding(20.dp)) }
                composable(route = catchFrogs) { CatchFrogScreen(modifier = Modifier.padding(20.dp)) }
                // TODO: Add more destinations
            }
        }
    }
}


@Composable
private fun MainBottomBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val catchFrogsScreen = stringResource(catchFrogs)
        val main = stringResource(main)
        val startCatchFrogs = stringResource(startCatchFrogs)
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Favorite, contentDescription = main) },
            label = { Text(main) },
            selected = false,
            onClick = { navController.navigate(main) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.PlayArrow, contentDescription = "start catch frogs") },
            label = { Text("catch frogs") },
            selected = false,
            onClick = { navController.navigate(startCatchFrogs) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.PlayArrow, contentDescription = "catch frogs") },
            label = { Text("catch frogs") },
            selected = false,
            onClick = { navController.navigate(catchFrogsScreen) }
        )
    }
}