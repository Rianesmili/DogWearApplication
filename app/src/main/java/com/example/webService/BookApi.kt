package com.example.webService

import retrofit2.Call
import retrofit2.http.GET

interface BookApi {

    @GET("author")
    fun getAllAuthors(): Call<Authors>
}