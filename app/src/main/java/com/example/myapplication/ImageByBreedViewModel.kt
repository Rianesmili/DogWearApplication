package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageByBreedViewModel : ViewModel() {

    val dogImageLiveData  = MutableLiveData<String>()

    fun getRandomImageByBreed(breed: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val dogRepository = DogRepository()
            val dogImage = dogRepository.getDogImageByBreed(breed)?.message
            dogImage?.let { dogImageLiveData.postValue(it) }
        }
    }
}