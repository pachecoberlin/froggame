package de.pacheco.froggame.core.domain.usecases

import de.pacheco.froggame.core.domain.game.engine.ICatchFrogEngine
import javax.inject.Inject

class StartCatchFrogUseCase @Inject constructor(private val catchFrogEngine: ICatchFrogEngine) {
    suspend operator fun invoke(rows: Int, cols: Int) {
        catchFrogEngine.startGame(rows * cols)
    }
}
