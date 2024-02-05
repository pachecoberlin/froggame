package de.pacheco.froggame.core.data.repos

import de.pacheco.froggame.core.data.repos.interfaces.IFrogDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FakeFrogDataRepository @Inject constructor() : IFrogDataRepository {
    override fun frogDatas(): Flow<List<String>> = flowOf(fakeFrogDatas)

    override suspend fun add(name: String) {
        fakeFrogDatas.add(name)
    }
}

val fakeFrogDatas = mutableListOf("One", "Two", "Three")
