package de.pacheco.froggame.core.data.repos.interfaces

import kotlinx.coroutines.flow.Flow

interface ICatchFrogRepository {
    fun startGame(numberOfFrogs: Int): Flow<List<Int>>
    fun stopGame()
    fun clearHighScore()
}