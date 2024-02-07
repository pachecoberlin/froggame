package de.pacheco.froggame.core.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.pacheco.froggame.core.database.AppDatabase
import de.pacheco.froggame.core.database.dao.FrogDataDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "FrogGameData"
        ).build()
    }

    @Provides
    fun provideFrogDataDao(appDatabase: AppDatabase): FrogDataDao {
        return appDatabase.frogDataDao()
    }
}
