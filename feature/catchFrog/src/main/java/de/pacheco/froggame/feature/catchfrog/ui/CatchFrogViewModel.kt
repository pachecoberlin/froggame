package de.pacheco.froggame.feature.catchfrog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.pacheco.froggame.core.domain.usecases.GetCatchFrogStateUseCase
import de.pacheco.froggame.core.domain.usecases.StartCatchFrogUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatchFrogViewModel @Inject constructor(
    private val startCatchFrogUseCase: StartCatchFrogUseCase,
    getCatchFrogStateUseCase: GetCatchFrogStateUseCase
) : ViewModel() {
    val caught: (Int) -> Unit = { catchedFrog ->
        viewModelScope.launch {
            println(catchedFrog) //TODO implement
        }
    }

    var rows: Int = 0
    var cols: Int = 0

    private val startGame: Pair<Function, (params: Map<Parameter, Any>) -> Unit> = Function.STARTGAME to { params ->
        rows = params[Parameter.ROWS] as? Int ?: 1
        cols = params[Parameter.COLS] as? Int ?: 1
        viewModelScope.launch { startCatchFrogUseCase(rows * cols) }
    }

    val functions: Map<Function, (params: Map<Parameter, Any>) -> Unit> = mapOf(startGame)

    val gameState: StateFlow<CatchFrogState> = getCatchFrogStateUseCase()
        .map {
            when (it) {
                -2 -> CatchFrogState.Preparation
                -1 -> CatchFrogState.GameOver
                else -> CatchFrogState.Running(it)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CatchFrogState.Preparation)
}


sealed interface CatchFrogState {
    data class Running(val frogIsShowing: Int) : CatchFrogState
    data object GameOver : CatchFrogState
    data object Preparation : CatchFrogState
}