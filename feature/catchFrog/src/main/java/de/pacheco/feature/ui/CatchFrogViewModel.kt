package de.pacheco.feature.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import de.pacheco.froggame.core.data.repos.CatchFrogRepository
import javax.inject.Inject

@HiltViewModel
class CatchFrogViewModel @Inject constructor(
    private val catchFrogRepository: CatchFrogRepository
) : ViewModel() {
    val startGame: Pair<Function, (params: Map<Parameter, Any>) -> Unit> = Function.STARTGAME to {
        //TODO navigate to catchFrogGameScreen and start the game
        println("FROM ViewModel ${it[Parameter.ROWS]} ${it[Parameter.COLS]}")
    }

    val functions: Map<Function, (params: Map<Parameter, Any>) -> Unit> = mapOf(startGame)
}