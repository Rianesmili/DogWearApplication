package com.example.bookmobileapp.di

import android.content.Context
import com.example.bookmobileapp.BookRepository
import com.example.bookmobileapp.MobileToWearComunicator
import com.example.bookmobileapp.webService.BookApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesBookRepository(bookApi: BookApi): BookRepository = BookRepository(bookApi)

    @Provides
    fun providesMobileToPhoneCommunicator(@ApplicationContext context: Context): MobileToWearComunicator{
        return MobileToWearComunicator(context)
    }
}