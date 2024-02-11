package de.pacheco.froggame.core.domain.game.engine

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.random.Random

class CatchFrogEngine @Inject constructor() : ICatchFrogEngine {
    private var frogCount: Int = 200
    private var delay: Long = 300L
    private val _actualScore = MutableStateFlow(0)
    override val actualScore: StateFlow<Int> = _actualScore.asStateFlow()

    fun updateScore(points: Int) {
        _actualScore.update { score -> score + points }
    }

    fun setScore(points: Int) {
        _actualScore.update { points }
    }

    private val _isStopped = MutableStateFlow(true)
    private val isStopped: StateFlow<Boolean> = _isStopped.asStateFlow()

    fun stop(isStopped: Boolean) {
        _isStopped.update { _ -> isStopped }
    }

    override val getFrogShowing: Flow<Int> = flow {
        while (true) {
            val value = Random.nextInt(frogCount)
            if (!isStopped.value) emit(value)
            delay(delay)
        }
    }

    override val isRunning: Flow<Boolean> = isStopped.map { !it }

    override suspend fun startGame(frogCount: Int, timeInSeconds: Int) {
        setScore(0)
        stop(false)
        this.frogCount = frogCount
        delay(timeInSeconds * 1000L)
        stop(true)
    }

    override suspend fun stopGame() {
        stop(true)
    }

    override suspend fun catchFrog(frogId: Int) {
        updateScore(100)
    }
}