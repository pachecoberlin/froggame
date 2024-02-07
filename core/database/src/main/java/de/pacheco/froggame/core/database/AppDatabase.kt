package de.pacheco.froggame.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import de.pacheco.froggame.core.database.dao.FrogDataDao
import de.pacheco.froggame.core.database.model.FrogData

@Database(entities = [FrogData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun frogDataDao(): FrogDataDao
}
