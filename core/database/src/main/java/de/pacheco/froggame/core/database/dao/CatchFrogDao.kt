package de.pacheco.froggame.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import de.pacheco.froggame.core.database.model.CatchFrog
import kotlinx.coroutines.flow.Flow

@Dao
interface CatchFrogDao {
    @Query("SELECT * FROM catchfrog ORDER BY uid DESC LIMIT 1")
    fun getCatchFrogs(): Flow<List<CatchFrog>>

    @Query("SELECT * FROM catchfrog WHERE uid = :id")
    suspend fun getCatchFrog(id: Long): CatchFrog

    // alternative    @Query("SELECT * FROM catchfrog ORDER BY highScore DESC LIMIT 1")
    @Query("SELECT uid,amount,max(highScore)AS highScore FROM catchfrog")
    fun getHighScore(): Flow<CatchFrog>

    @Query("SELECT * FROM catchfrog WHERE amount = :amount")
    fun getHighScoreByAmount(amount: Int): Flow<CatchFrog>

    @Upsert
    suspend fun addOrReplaceCatchFrog(data: CatchFrog): Long
}