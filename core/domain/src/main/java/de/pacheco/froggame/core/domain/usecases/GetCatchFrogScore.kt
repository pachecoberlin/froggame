package de.pacheco.froggame.core.domain.usecases

import de.pacheco.froggame.core.domain.game.engine.ICatchFrogEngine
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatchFrogScore @Inject constructor(catchFrogEngine: ICatchFrogEngine) {
    private val score = catchFrogEngine.actualScore

    operator fun invoke(): Flow<Int> {
        return score
    }
}