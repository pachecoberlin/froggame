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

    private val startGame: Pair<Function, (params: Map<Parameter, Any>) -> Unit> = Function.STARTGAME to { params ->
        viewModelScope.launch { startCatchFrogUseCase(params[Parameter.ROWS] as? Int ?: 1, params[Parameter.COLS] as? Int ?: 1) }
    }

    val functions: Map<Function, (params: Map<Parameter, Any>) -> Unit> = mapOf(startGame)

    val gameState: StateFlow<CatchFrogState> = getCatchFrogStateUseCase().map { if (it == -1) CatchFrogState.GameOver else CatchFrogState.Running(it) }.stateIn(
        viewModelScope, SharingStarted
            .WhileSubscribed(5000), CatchFrogState.Running(0)
    )
}

sealed interface CatchFrogState {
    data class Running(val frogIsShowing: Int) : CatchFrogState
    data object GameOver : CatchFrogState
}