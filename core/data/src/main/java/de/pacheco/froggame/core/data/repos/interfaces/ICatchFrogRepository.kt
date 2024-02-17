package de.pacheco.froggame.core.data.repos.interfaces

import kotlinx.coroutines.flow.Flow

interface ICatchFrogRepository {
    fun clearHighScore()
    fun highScore(frogAmount: Int): Flow<Int>
    suspend fun saveScore(score: Int, frogCount: Int)
}