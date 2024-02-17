package de.pacheco.froggame.core.data.repos

import de.pacheco.froggame.core.data.repos.interfaces.ICatchFrogRepository
import de.pacheco.froggame.core.database.dao.CatchFrogDao
import de.pacheco.froggame.core.database.model.CatchFrog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CatchFrogRepository @Inject constructor(private val catchFrogDao: CatchFrogDao) : ICatchFrogRepository {

    override fun clearHighScore() {
//        TODO("Not yet implemented")
    }

    override fun highScore(frogAmount: Int): Flow<Int> {
        return catchFrogDao.getHighScore().map {
            it.highScore
        }
    }

    override suspend fun saveScore(score: Int, frogCount: Int) {
        val highScoreByAmount = catchFrogDao.getHighScoreByAmount(frogCount)
        highScoreByAmount.collectLatest {
            if (it == null) {
                catchFrogDao.addOrReplaceCatchFrog(CatchFrog(amount = frogCount, highScore = score))
            } else if (score > it.highScore) {
                catchFrogDao.addOrReplaceCatchFrog(CatchFrog(uid = it.uid, amount = frogCount, highScore = score))
            }
        }
    }
}
