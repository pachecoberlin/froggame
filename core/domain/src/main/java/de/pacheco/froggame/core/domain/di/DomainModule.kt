package de.pacheco.froggame.core.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.pacheco.froggame.core.domain.game.engine.CatchFrogEngine
import de.pacheco.froggame.core.domain.game.engine.ICatchFrogEngine
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Singleton
    @Binds
    fun bindsCatchFrogEngine(
        catchFrogEngine: CatchFrogEngine
    ): ICatchFrogEngine

}