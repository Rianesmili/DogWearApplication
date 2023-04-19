package com.example.bookmobileapp.di

import com.example.bookmobileapp.webService.BookApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)

object RetrofitModule {


    const val BOOK_BASE_URL = "https://poetrydb.org/"


    @Provides
    @Named("book")
    fun providesBookRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BOOK_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()

    @Provides
    fun providesBookApi(@Named("book") retrofit: Retrofit): BookApi = retrofit.create(
        BookApi::class.java
    )

}