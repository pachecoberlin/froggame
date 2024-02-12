package de.pacheco.froggame.core.data.repos

import de.pacheco.froggame.core.data.repos.interfaces.ICatchFrogRepository
import de.pacheco.froggame.core.database.dao.CatchFrogDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CatchFrogRepository @Inject constructor(private val catchFrogDao: CatchFrogDao) : ICatchFrogRepository {

    override fun clearHighScore() {
//        TODO("Not yet implemented")
    }

    override fun highScore(frogAmount: Int): Flow<Int> {
        return catchFrogDao.getHighScoreByAmount(frogAmount).map { it?.highscore ?:0 }
    }
}