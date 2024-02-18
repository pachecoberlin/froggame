package de.pacheco.froggame.core.domain.game.engine

import kotlinx.coroutines.flow.Flow

interface ICatchFrogEngine {
    val actualScore: Flow<Int>
    val getFrogShowing: Flow<Int>
    /**
     * returns true when stopped and false while running
     */
    val isRunning: Flow<Boolean>
    val timeRemaining: Flow<Int>
    /**
     * Will start the game. getFrogShowing will emit values between 0 and frogCount for duration seconds.
     */
    suspend fun startGame(frogCount: Int = 16, timeInSeconds: Int = 10)
    suspend fun stopGame()
    /**
     * Updates the actualScore
     */
    suspend fun catchFrog(frogId: Int)
}
