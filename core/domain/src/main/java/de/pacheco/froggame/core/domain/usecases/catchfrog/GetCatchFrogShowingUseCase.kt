package de.pacheco.froggame.core.domain.usecases.catchfrog

import de.pacheco.froggame.core.domain.game.engine.ICatchFrogEngine
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatchFrogShowingUseCase @Inject constructor(catchFrogEngine: ICatchFrogEngine) {
    private val frogShowing = catchFrogEngine.getFrogShowing
    operator fun invoke(): Flow<Int> {
        return frogShowing
    }
}