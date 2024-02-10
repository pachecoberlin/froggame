package de.pacheco.froggame.core.domain.game.engine

import de.pacheco.froggame.core.data.repos.interfaces.ICatchFrogRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.random.Random

class CatchFrogEngine @Inject constructor(private val catchFrogRepository: ICatchFrogRepository) : ICatchFrogEngine {
    private var frogCount: Int = 200
    private var actualScore: Int = 0
    private var delay: Long = 300L
    private var isStopped: Boolean = true

    override val getGameData: Flow<Int> = flow {
        while (true) {
            val value = Random.nextInt(frogCount)
            println("$value $this@CatchFrogEngine")
            emit(if (isStopped) -2 else value)
            delay(delay)
        }
    }


    override suspend fun startGame(frogCount: Int) {
        isStopped = false
        this.frogCount = frogCount
        delay(10000L)
        isStopped = true
    }

    override suspend fun stopGame() {
        isStopped = true
        catchFrogRepository.stopGame(actualScore)
    }
}