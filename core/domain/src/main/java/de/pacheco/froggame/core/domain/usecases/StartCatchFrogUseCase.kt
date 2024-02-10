package de.pacheco.froggame.core.domain.usecases

import de.pacheco.froggame.core.domain.game.engine.ICatchFrogEngine
import javax.inject.Inject

class StartCatchFrogUseCase @Inject constructor(private val catchFrogEngine: ICatchFrogEngine) {
    suspend operator fun invoke(frogcount: Int) {
        catchFrogEngine.startGame(frogcount)
    }
}
