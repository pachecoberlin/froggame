package de.pacheco.froggame.core.domain.usecases

import de.pacheco.froggame.core.domain.game.engine.ICatchFrogEngine
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatchFrogStateUseCase @Inject constructor(private val catchFrogEngine: ICatchFrogEngine) {
    operator fun invoke():Flow<Int> {
        return catchFrogEngine.getGameData()
    }
}