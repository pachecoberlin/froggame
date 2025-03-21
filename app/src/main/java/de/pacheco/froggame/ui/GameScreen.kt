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
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import de.pacheco.froggame.R
import de.pacheco.froggame.core.ressources.R.string
import de.pacheco.froggame.ui.components.GameButton
import de.pacheco.froggame.viewmodel.MainViewModel

@Composable
fun GameScreen(navController: NavHostController?) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_tutorial),
            contentDescription = "A dark blurry room everywhere is blood and dead people",
            contentScale = ContentScale.Crop
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

        }
    }
}

@Preview
@Composable
fun GameScreen(){
    GameScreen(null)
}
