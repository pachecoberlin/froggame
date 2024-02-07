package de.pacheco.froggame.core.data.repos

import de.pacheco.froggame.core.data.repos.interfaces.IFrogDataRepository
import de.pacheco.froggame.core.database.dao.FrogDataDao
import de.pacheco.froggame.core.database.model.FrogData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
