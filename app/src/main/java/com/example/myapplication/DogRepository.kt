package com.example.myapplication

import android.os.StrictMode
import com.example.webService.DogApi
import com.example.webService.DogBreeds
import com.example.webService.DogImage
import com.example.webService.RetrofitClient

class DogRepository {

    fun getRondomDogImage(): DogImage? {

            val dogApi: DogApi = RetrofitClient.getRetrofitInstance().create(
            DogApi::class.java
        )

        return dogApi.getImageRandom().execute().body()
    }

    fun getDogBreed(): DogBreeds?{
        val dogApi: DogApi = RetrofitClient.getRetrofitInstance().create(
            DogApi::class.java
        )
        return dogApi.getAllBreeds().execute().body()
    }

    fun getDogImageByBreed(breed: String): DogImage? {
        val dogApi: DogApi = RetrofitClient.getRetrofitInstance().create(
            DogApi::class.java
        )
        return dogApi.getRondomImageByBreed(breed).execute().body()
    }

}