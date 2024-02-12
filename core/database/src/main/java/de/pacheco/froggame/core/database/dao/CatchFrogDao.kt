package de.pacheco.froggame.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.pacheco.froggame.core.database.model.CatchFrog
import kotlinx.coroutines.flow.Flow

@Dao
interface CatchFrogDao {
    @Query("SELECT * FROM catchfrog ORDER BY uid DESC LIMIT 1")
    fun getCatchFrogs(): Flow<List<CatchFrog>>

    @Query("SELECT * FROM catchfrog WHERE uid = :id")
    suspend fun getCatchFrog(id: Long): CatchFrog

    @Query("SELECT * FROM catchfrog WHERE amount = :amount")
    fun getHighScoreByAmount(amount: Int): Flow<CatchFrog>

    @Insert
    suspend fun addCatchFrog(data: CatchFrog):Long
}