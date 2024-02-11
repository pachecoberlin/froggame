package de.pacheco.froggame.core.domain.usecases

import de.pacheco.froggame.core.domain.game.engine.ICatchFrogEngine
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatchFrogStateUseCase @Inject constructor(catchFrogEngine: ICatchFrogEngine) {
    private val gameData = catchFrogEngine.isRunning
    operator fun invoke(): Flow<Boolean> {
        return gameData
    }
}