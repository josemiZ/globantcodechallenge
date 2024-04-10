package com.josemiz.globantcodechallenge.domain

import com.josemiz.globantcodechallenge.data.repository.EventsRepository
import com.josemiz.globantcodechallenge.domain.usecase.EventsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModules {

    @Singleton
    @Provides
    fun provideEventsUseCase(eventsRepository: EventsRepository): EventsUseCase {
        return EventsUseCase(eventsRepository)
    }
}