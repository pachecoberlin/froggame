package de.pacheco.froggame.core.domain.usecases

import de.pacheco.froggame.core.domain.game.engine.ICatchFrogEngine
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatchFrogStateUseCase @Inject constructor(catchFrogEngine: ICatchFrogEngine) {
    private val gameData = catchFrogEngine.getGameData
    operator fun invoke(): Flow<Int> {
        return gameData
    }
}