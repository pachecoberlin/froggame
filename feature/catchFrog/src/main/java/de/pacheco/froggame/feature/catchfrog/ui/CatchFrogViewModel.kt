package de.pacheco.froggame.feature.catchfrog.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import de.pacheco.froggame.core.data.repos.CatchFrogRepository
import de.pacheco.froggame.core.domain.StartGameUseCase
import javax.inject.Inject

@HiltViewModel
class CatchFrogViewModel @Inject constructor(
    private val startGameUseCase: StartGameUseCase
) : ViewModel() {
    val startGame: Pair<Function, (params: Map<Parameter, Any>) -> Unit> = Function.STARTGAME to { params ->
        startGameUseCase(params[Parameter.ROWS] as? Int?:4,params[Parameter.COLS] as? Int?:4)
    }

    val functions: Map<Function, (params: Map<Parameter, Any>) -> Unit> = mapOf(startGame)
}