package de.pacheco.froggame.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.pacheco.froggame.repos.StringRepository
import de.pacheco.froggame.repos.interfaces.IStringRepository
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Singleton
    @Binds
    fun bindsStringRepository(
        stringRepository: StringRepository
    ): IStringRepository
}

@Module
@InstallIn(SingletonComponent::class)
object ContextModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context
}