package de.pacheco.froggame.core.data.repos

import de.pacheco.froggame.core.data.repos.interfaces.ICatchFrogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatchFrogRepository @Inject constructor():ICatchFrogRepository  {
    override fun startGame(numberOfFrogs: Int): Flow<List<Int>> {
        TODO("Not yet implemented")
    }

    override fun stopGame() {
        TODO("Not yet implemented")
    }

    override fun clearHighScore() {
        TODO("Not yet implemented")
    }
}