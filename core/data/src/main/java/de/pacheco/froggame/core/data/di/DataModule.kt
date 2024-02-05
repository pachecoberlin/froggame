package de.pacheco.froggame.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.pacheco.froggame.core.data.repos.CatchFrogRepository
import de.pacheco.froggame.core.data.repos.DefaultFrogDataRepository
import de.pacheco.froggame.core.data.repos.interfaces.ICatchFrogRepository
import de.pacheco.froggame.core.data.repos.interfaces.IFrogDataRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsFrogDataRepository(
        frogDataRepository: DefaultFrogDataRepository
    ): IFrogDataRepository
    @Singleton
    @Binds
    fun bindsCatchFrogRepository(
        frogDataRepository: CatchFrogRepository
    ): ICatchFrogRepository
}
