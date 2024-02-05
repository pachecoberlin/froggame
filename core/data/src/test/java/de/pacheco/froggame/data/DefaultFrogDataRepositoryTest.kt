/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.pacheco.froggame.data

import de.pacheco.froggame.core.data.repos.DefaultFrogDataRepository
import de.pacheco.froggame.core.database.FrogData
import de.pacheco.froggame.core.database.FrogDataDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for [DefaultFrogDataRepository].
 */
class DefaultFrogDataRepositoryTest {

    @Test
    fun frogDatas_newItemSaved_itemIsReturned() = runTest {
        val repository = DefaultFrogDataRepository(FakeFrogDataDao())

        repository.add("Repository")

        assertEquals(repository.frogDatas().first().size, 1)
    }

}

private class FakeFrogDataDao : FrogDataDao {

    private val data = mutableListOf<FrogData>()

    override fun getFrogDatas(): Flow<List<FrogData>> = flow {
        emit(data)
    }

    override suspend fun insertFrogData(item: FrogData) {
        data.add(0, item)
    }
}
