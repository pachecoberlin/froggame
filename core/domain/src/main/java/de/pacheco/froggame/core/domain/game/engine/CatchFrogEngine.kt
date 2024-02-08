package de.pacheco.froggame.core.domain.game.engine

import de.pacheco.froggame.core.data.repos.interfaces.ICatchFrogRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.concurrent.timer
import kotlin.random.Random

class CatchFrogEngine @Inject constructor(private val catchFrogRepository: ICatchFrogRepository) : ICatchFrogEngine {
    private var frogCount: Int = 1
    private var actualScore: Int = 0
    private var delay: Long = 300

    override fun getGameData(): Flow<Int> {
        return flow {
            var running = true
            timer(initialDelay = 10000L, period = 1L) {
                running = false
                this.cancel()
            }
            while (running) {
                emit(Random.nextInt(frogCount))
                delay(delay)
            }
            emit(-1)
            val job = SupervisorJob()
            val scope = CoroutineScope(Dispatchers.IO + job)
            scope.launch { stopGame() }
        }
    }

    override suspend fun startGame(frogCount: Int) {
        this.frogCount = frogCount
    }

    override suspend fun stopGame() {
        catchFrogRepository.stopGame(actualScore)
    }
}