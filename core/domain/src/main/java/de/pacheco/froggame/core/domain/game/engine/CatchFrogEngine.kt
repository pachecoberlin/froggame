package de.pacheco.froggame.core.domain.game.engine

import de.pacheco.froggame.core.data.repos.interfaces.ICatchFrogRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.random.Random

class CatchFrogEngine @Inject constructor(private val catchFrogRepository: ICatchFrogRepository) : ICatchFrogEngine {
    private var actualScore: Int = 0
    private var delay: Long = 300

    override fun startGame(frogCount: Int): Flow<Int> {
        return flow {
            while (true) {
                emit(Random.nextInt(0, frogCount))
                delay(delay)
            }
        }
    }

    override suspend fun stopGame() {
        catchFrogRepository.stopGame(actualScore)
    }
}