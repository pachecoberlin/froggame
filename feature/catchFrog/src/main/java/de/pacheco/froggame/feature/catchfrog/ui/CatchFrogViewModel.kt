package de.pacheco.froggame.feature.catchfrog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.pacheco.froggame.core.domain.usecases.CatchFrogUseCase
import de.pacheco.froggame.core.domain.usecases.GetCatchFrogHighScoreUseCase
import de.pacheco.froggame.core.domain.usecases.GetCatchFrogScoreUseCase
import de.pacheco.froggame.core.domain.usecases.GetCatchFrogShowingUseCase
import de.pacheco.froggame.core.domain.usecases.GetCatchFrogStateUseCase
import de.pacheco.froggame.core.domain.usecases.GetCatchFrogTimeRemainingUseCase
import de.pacheco.froggame.core.domain.usecases.SaveScoreUseCase
import de.pacheco.froggame.core.domain.usecases.StartCatchFrogUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatchFrogViewModel @Inject constructor(
    private val catchFrogUseCase: CatchFrogUseCase,
    private val saveScoreUseCase: SaveScoreUseCase,
    private val startCatchFrogUseCase: StartCatchFrogUseCase,
    getCatchFrogStateUseCase: GetCatchFrogStateUseCase,
    getCatchFrogShowingUseCase: GetCatchFrogShowingUseCase,
    getCatchFrogScoreUseCase: GetCatchFrogScoreUseCase,
    getCatchFrogHighScoreUseCase: GetCatchFrogHighScoreUseCase,
    getCatchFrogTimeRemainingUseCase: GetCatchFrogTimeRemainingUseCase,
) : ViewModel() {

    fun caughtFrog(caughtFrog: Int) {
        viewModelScope.launch {
            catchFrogUseCase(caughtFrog)
        }
    }

    private var rows: Int = -1
    private var cols: Int = -1
    private var oldValue: CatchFrogState = CatchFrogState.Preparation

    private val startGame: Pair<Function, (params: Map<Parameter, Any>) -> Unit> = Function.STARTGAME to { params ->
        rows = params[Parameter.ROWS] as? Int ?: 1
        cols = params[Parameter.COLS] as? Int ?: 1
        startGame(rows, cols)
    }

    fun startGame(rows: Int, cols: Int) {
        viewModelScope.launch { startCatchFrogUseCase(rows * cols) }
    }

    val functions: Map<Function, (params: Map<Parameter, Any>) -> Unit> = mapOf(startGame)

    /**
     * Maybe we should have two StateFlows. One with the frogShowingUC and one which gives us the gamestate.
     * This here might be to much of some logic in the presentation layer
     */
    val gameState: StateFlow<CatchFrogState> = getCatchFrogStateUseCase()
        .combineTransform(getCatchFrogShowingUseCase()) { isRunning, frogShown ->
            if (isRunning) {
                oldValue = CatchFrogState.Running(frogShown)
                emit(oldValue)
            } else {
                if (oldValue is CatchFrogState.Running) {
                    oldValue = CatchFrogState.GameOver
                    emit(oldValue)
                    viewModelScope.launch { saveScoreUseCase.invoke(score.value,rows * cols)}
                } else {
                    oldValue = CatchFrogState.Preparation
                    emit(oldValue)
                }
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CatchFrogState.Preparation)

    val score: StateFlow<Int> = getCatchFrogScoreUseCase().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
    val highScore: StateFlow<Int> = getCatchFrogHighScoreUseCase(rows * cols).stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
    val time: StateFlow<Int> = getCatchFrogTimeRemainingUseCase().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
}


sealed interface CatchFrogState {
    data class Running(val frogIsShowing: Int) : CatchFrogState
    data object GameOver : CatchFrogState
    data object Preparation : CatchFrogState
}