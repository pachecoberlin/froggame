package de.pacheco.froggame.core.data.repos.interfaces

import kotlinx.coroutines.flow.Flow

interface IFrogDataRepository {
    fun frogDatas(): Flow<List<String>>

    suspend fun add(name: String)
}