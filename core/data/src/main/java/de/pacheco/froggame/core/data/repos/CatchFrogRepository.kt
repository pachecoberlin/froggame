package de.pacheco.froggame.core.data.repos

import de.pacheco.froggame.core.data.repos.interfaces.ICatchFrogRepository
import de.pacheco.froggame.core.database.dao.CatchFrogDao
import de.pacheco.froggame.core.database.model.CatchFrog
import javax.inject.Inject

class CatchFrogRepository @Inject constructor(private val catchFrogDao: CatchFrogDao) : ICatchFrogRepository {
    override suspend fun startGame(numberOfFrogs: Int): Int {
        val catchFrog = catchFrogDao.getHighScoreByAmount(numberOfFrogs) ?: CatchFrog(amount = numberOfFrogs, highscore = 0)
        val uid = catchFrogDao.addCatchFrog(catchFrog)
        return catchFrog.highscore
    }

    override fun stopGame(highscore: Int) {
//        TODO("Not yet implemented")
    }

    override fun clearHighScore() {
//        TODO("Not yet implemented")
    }
}