package de.pacheco.froggame.core.domain.usecases

import de.pacheco.froggame.core.domain.game.engine.ICatchFrogEngine
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StartGameUseCase @Inject constructor(private val catchFrogEngine: ICatchFrogEngine) {
    operator fun invoke(rows: Int, cols: Int): Flow<Int> {
        return catchFrogEngine.startGame(rows * cols)
    }
}
