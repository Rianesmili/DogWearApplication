package com.example.myapplication

import com.example.webService.DogApi
import com.example.webService.DogBreeds
import com.example.webService.DogImage


class DogRepository (private val dogApi: DogApi) {

    fun getRondomDogImage(): DogImage? = dogApi.getImageRandom().execute().body()

    fun getDogBreed(): DogBreeds? = dogApi.getAllBreeds().execute().body()

    fun getDogImageByBreed(breed: String): DogImage? =
        dogApi.getRondomImageByBreed(breed).execute().body()


}