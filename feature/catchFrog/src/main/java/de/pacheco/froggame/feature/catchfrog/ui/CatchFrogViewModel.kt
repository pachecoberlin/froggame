package de.pacheco.froggame.feature.catchfrog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.pacheco.froggame.core.domain.usecases.CatchFrogUseCase
import de.pacheco.froggame.core.domain.usecases.GetCatchFrogScore
import de.pacheco.froggame.core.domain.usecases.GetCatchFrogShowingUseCase
import de.pacheco.froggame.core.domain.usecases.GetCatchFrogStateUseCase
import de.pacheco.froggame.core.domain.usecases.StartCatchFrogUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatchFrogViewModel @Inject constructor(
    private val catchFrogUseCase: CatchFrogUseCase,
    private val startCatchFrogUseCase: StartCatchFrogUseCase,
    getCatchFrogStateUseCase: GetCatchFrogStateUseCase,
    getCatchFrogShowingUseCase: GetCatchFrogShowingUseCase,
    getCatchFrogScore: GetCatchFrogScore,
) : ViewModel() {

    fun caughtFrog(caughtFrog:Int) {
        viewModelScope.launch {
            catchFrogUseCase(caughtFrog)
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
        .combine(getCatchFrogShowingUseCase()) { isRunning, frogShown ->
            when (isRunning) {
                true -> CatchFrogState.Running(frogShown)
                else -> CatchFrogState.Preparation
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CatchFrogState.Preparation)

    val score: StateFlow<Int> = getCatchFrogScore().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
}


sealed interface CatchFrogState {
    data class Running(val frogIsShowing: Int) : CatchFrogState
    data object GameOver : CatchFrogState
    data object Preparation : CatchFrogState
}