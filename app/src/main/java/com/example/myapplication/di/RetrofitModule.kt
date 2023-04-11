package com.example.myapplication.di

import com.example.webService.BookApi
import com.example.webService.DogApi
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

    private const val BASE_URL = "https://dog.ceo/"
    private const val BOOK_BASE_URL = "https://poetrydb.org/"

    @Provides
    @Named("dog")
    fun providesDogRetrofit(): Retrofit =
        Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build()

    @Provides
    @Named("book")
    fun providesBookRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BOOK_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()

    fun providesBookApi(@Named("book") retrofit: Retrofit): BookApi {
        return retrofit.create(
            BookApi::class.java
        )
    }


    @Provides
    fun providesDogApi(@Named("dog")retrofit: Retrofit): DogApi {
        return retrofit.create(
            DogApi::class.java
        )
    }
}