package com.example.myapplication.di

import com.example.myapplication.DogRepository
import com.example.webService.DogApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun providesDogRepository(dogApi: DogApi): DogRepository = DogRepository(dogApi)
}