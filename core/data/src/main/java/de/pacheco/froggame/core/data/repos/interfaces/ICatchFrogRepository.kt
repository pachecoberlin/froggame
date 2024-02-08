package de.pacheco.froggame.core.data.repos.interfaces

import kotlinx.coroutines.flow.Flow

interface ICatchFrogRepository {
    fun clearHighScore()
    suspend fun startGame(numberOfFrogs: Int): Int
    fun stopGame(highscore:Int)
}