package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomDogImageViewModel @Inject constructor(
    private val dogRepository: DogRepository
) : ViewModel() {

    val urlLiveData = MutableLiveData<String>()


    fun getRondomDogImageUrl() {
        viewModelScope.launch(Dispatchers.IO) {
            urlLiveData.postValue(dogRepository.getRondomDogImage()?.message.orEmpty())
        }
    }

}