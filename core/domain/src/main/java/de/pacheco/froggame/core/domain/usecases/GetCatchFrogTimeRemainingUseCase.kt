package de.pacheco.froggame.core.domain.usecases

import de.pacheco.froggame.core.domain.game.engine.ICatchFrogEngine
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatchFrogTimeRemainingUseCase @Inject constructor(catchFrogEngine: ICatchFrogEngine) {
    private val timeRemaining = catchFrogEngine.timeRemaining
    operator fun invoke(): Flow<Int> {
        return timeRemaining
    }
}