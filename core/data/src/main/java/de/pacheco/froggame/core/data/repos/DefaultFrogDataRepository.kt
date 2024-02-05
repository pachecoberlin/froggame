package de.pacheco.froggame.core.data.repos

import de.pacheco.froggame.core.data.repos.interfaces.IFrogDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import de.pacheco.froggame.core.database.FrogData
import de.pacheco.froggame.core.database.FrogDataDao
import javax.inject.Inject

class DefaultFrogDataRepository @Inject constructor(
    private val frogDataDao: FrogDataDao
) : IFrogDataRepository {

    override fun frogDatas(): Flow<List<String>> =
        frogDataDao.getFrogDatas().map { items -> items.map { it.name } }

    override suspend fun add(name: String) {
        frogDataDao.insertFrogData(FrogData(name = name))
    }
}
