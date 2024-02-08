package de.pacheco.froggame.core.domain

interface ICatchFrogEngine {
  fun startGame(frogCount:Int):Flow<Int>
  suspend fun stopGame()
}
