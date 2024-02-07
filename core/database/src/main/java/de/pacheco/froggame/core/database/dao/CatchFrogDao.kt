package de.pacheco.froggame.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import de.pacheco.froggame.core.database.model.CatchFrog
import kotlinx.coroutines.flow.Flow

@Dao
interface CatchFrogDao {
    @Query("SELECT * FROM catchfrog ORDER BY uid DESC LIMIT 1")
    fun getCatchFrog(): Flow<List<CatchFrog>>
}