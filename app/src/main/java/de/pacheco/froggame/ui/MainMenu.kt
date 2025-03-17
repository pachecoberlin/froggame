package de.pacheco.froggame.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.pacheco.froggame.R
import de.pacheco.froggame.core.ressources.R.string
import de.pacheco.froggame.feature.catchfrog.ui.StartCatchFrogScreen
import de.pacheco.froggame.feature.frogdata.ui.FrogDataScreen
import de.pacheco.froggame.ui.components.GameButton

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val newGame = stringResource(string.newGame)
    val mainMenu = stringResource(string.mainMenu)
    val startCatchFrogs = stringResource(string.startCatchFrogs)
    val options = stringResource(id = string.options)
    NavHost(navController = navController, startDestination = mainMenu) {
        composable(mainMenu) { MainMenu(navController) }
        composable(newGame) { FrogDataScreen(modifier = Modifier.padding(16.dp)) }
        composable(route = startCatchFrogs) { StartCatchFrogScreen(modifier = Modifier.padding(20.dp)) }
        // TODO: Add more destinations
    }
}

@Composable
fun MainMenu(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
//        padding ->

        Image(
            painter = painterResource(id = R.drawable.background_fantasy),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
//                .consumeWindowInsets(padding)
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))


            val newGame = stringResource(string.newGame)
            GameButton(text = newGame) {
                navController.navigate(newGame)
            }
            GameButton(text = stringResource(id = string.save)) {
                // Navigate to SaveLoadScreen
            }
            GameButton(text = stringResource(id = string.load)) {
                // Navigate to SaveLoadScreen
            }
            val options = stringResource(id = string.options)
            GameButton(text = options) {
                navController.navigate(options)
            }
        }
    }
}