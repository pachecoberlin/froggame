package de.pacheco.froggame.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.pacheco.froggame.R
import de.pacheco.froggame.feature.catchfrog.ui.StartCatchFrogScreen
import de.pacheco.froggame.ui.components.GameButton
import de.pacheco.froggame.viewmodel.MainViewModel

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val strings = hiltViewModel<MainViewModel>().stringRepository
    NavHost(navController = navController, startDestination = strings.mainMenu) {
        composable(strings.game) { GameScreen() }
        composable(strings.mainMenu) { MainMenu(navController) }
        composable(strings.newGame) { TutorialScreen(navController) }
        composable(strings.options) { OptionsScreen() }
        composable(strings.saveLoad) { SaveLoadScreen() }
        composable(strings.startCatchFrogs) { StartCatchFrogScreen(modifier = Modifier.padding(20.dp)) }
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
            contentDescription = "A wonderful fantasy world with dragons, rivers and mountains",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
//                .consumeWindowInsets(padding)
        ) {
            val strings = hiltViewModel<MainViewModel>().stringRepository
            Text(
                text = stringResource(id = R.string.app_name).uppercase(),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            val newGame = strings.newGame
            GameButton(text = newGame) {
                navController.navigate(newGame)
            }
            GameButton(text = strings.save) {
                navController.navigate(strings.saveLoad)
            }
            GameButton(text = strings.load) {
                navController.navigate(strings.saveLoad)
            }
            val options = strings.options
            GameButton(text = options) {
                navController.navigate(options)
            }
        }
    }
}