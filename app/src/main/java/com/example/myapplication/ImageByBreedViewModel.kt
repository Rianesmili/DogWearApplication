package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageByBreedViewModel @Inject constructor(
    private val dogRepository: DogRepository
) : ViewModel() {

    val dogImageLiveData  = MutableLiveData<String>()


    fun getRandomImageByBreed(breed: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val dogImage = dogRepository.getDogImageByBreed(breed)?.message
            dogImage?.let { dogImageLiveData.postValue(it) }
        }
    }
}