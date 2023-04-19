package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.BookRepository
import com.example.myapplication.DogRepository
import com.example.myapplication.WearToPhoneCommunicator
import com.example.webService.BookApi
import com.example.webService.DogApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun providesDogRepository(dogApi: DogApi): DogRepository = DogRepository(dogApi)

    @Provides
    fun providesBookRepository(bookApi: BookApi): BookRepository = BookRepository(bookApi)

    @Provides
    fun providesWearToPhoneCommunicator(@ApplicationContext context: Context): WearToPhoneCommunicator {
        return WearToPhoneCommunicator(context)
    }
}