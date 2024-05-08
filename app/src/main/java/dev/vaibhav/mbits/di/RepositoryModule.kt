package dev.vaibhav.mbits.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.vaibhav.mbits.data.BreathingToolRepositoryImpl
import dev.vaibhav.mbits.domain.BreathingToolRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideBreathingToolRepository(
        repository: BreathingToolRepositoryImpl,
    ): BreathingToolRepository
}