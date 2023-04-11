package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RandomDogImageViewModel : ViewModel() {

    val urlLiveData = MutableLiveData<String>()

    fun getRondomDogImageUrl() {
        viewModelScope.launch(Dispatchers.IO) {
            val dogRepository = DogRepository()
            urlLiveData.postValue(dogRepository.getRondomDogImage()?.message.orEmpty())
        }
    }

}