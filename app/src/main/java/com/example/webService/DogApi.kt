package com.example.webService

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET("api/breeds/image/random")
    fun getImageRandom(): Call<DogImage>

    @GET("api/breeds/list/all")
    fun getAllBreeds(): Call<DogBreeds>

    @GET("/api/breed/{breed}/images/random")
    fun getRondomImageByBreed(@Path("breed") breed: String): Call<DogImage>
}