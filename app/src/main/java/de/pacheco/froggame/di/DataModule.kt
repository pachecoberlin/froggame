package de.pacheco.froggame.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.pacheco.froggame.repos.StringRepository
import de.pacheco.froggame.repos.interfaces.IStringRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsStringRepository(
        stringRepository: StringRepository
    ): IStringRepository
}
