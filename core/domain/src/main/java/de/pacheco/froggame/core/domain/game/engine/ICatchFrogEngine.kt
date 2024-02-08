package de.pacheco.froggame.core.domain.game.engine

import kotlinx.coroutines.flow.Flow

interface ICatchFrogEngine {
  fun startGame(frogCount:Int): Flow<Int>
  suspend fun stopGame()
}
