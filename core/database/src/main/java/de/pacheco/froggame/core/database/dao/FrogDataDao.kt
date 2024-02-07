package de.pacheco.froggame.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.pacheco.froggame.core.database.model.FrogData
import kotlinx.coroutines.flow.Flow

@Dao
interface FrogDataDao {
    @Query("SELECT * FROM frogdata ORDER BY uid DESC LIMIT 10")
    fun getFrogDatas(): Flow<List<FrogData>>

    @Insert
    suspend fun insertFrogData(item: FrogData)
}