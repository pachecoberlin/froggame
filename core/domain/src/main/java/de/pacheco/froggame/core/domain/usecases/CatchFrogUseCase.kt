package de.pacheco.froggame.core.domain.usecases

import de.pacheco.froggame.core.domain.game.engine.ICatchFrogEngine
import javax.inject.Inject

class CatchFrogUseCase @Inject constructor(private val catchFrogEngine: ICatchFrogEngine) {
    suspend operator fun invoke(frogId: Int) {
        catchFrogEngine.catchFrog(frogId)
    }
}
