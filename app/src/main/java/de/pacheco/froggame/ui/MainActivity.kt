package de.pacheco.froggame.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import de.pacheco.froggame.core.data.repos.interfaces.ISoundRepository
import de.pacheco.froggame.core.ui.FrogMainTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /**
     * I do not like this dependency, but no idea how to get rid of it. The MediaPlayer(s) need to be released. While navigating through the app it should probably not happen
     * or should it?
     */
    @Inject
    lateinit var soundRepository: ISoundRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FrogMainTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation()
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        soundRepository.releasePlayers()
    }
}
