package de.pacheco.froggame.core.data.repos

import de.pacheco.froggame.core.data.repos.interfaces.ICatchFrogRepository
import de.pacheco.froggame.core.database.dao.CatchFrogDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CatchFrogRepository @Inject constructor(private val catchFrogDao: CatchFrogDao) : ICatchFrogRepository {
    override fun startGame(numberOfFrogs: Int): Flow<List<Int>> {
        return catchFrogDao.getCatchFrog().map { it.map { it.amount } }
    }

    override fun stopGame() {
//        TODO("Not yet implemented")
    }

    override fun clearHighScore() {
//        TODO("Not yet implemented")
    }
}