package de.pacheco.froggame.core.domain.game.engine

import kotlinx.coroutines.flow.Flow

interface ICatchFrogEngine {
  val getGameData: Flow<Int>
  suspend fun startGame(frogCount:Int)
  suspend fun stopGame()
}
