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
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesBookRepository(
        bookApi: BookApi,
        @ApplicationContext context: Context
    ): BookRepository = BookRepository(bookApi, context)

    @Provides
    fun providesMobileToPhoneCommunicator(@ApplicationContext context: Context): MobileToWearComunicator {
        return MobileToWearComunicator(context)
    }
}