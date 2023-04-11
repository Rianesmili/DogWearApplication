package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogBreedsViewModel @Inject constructor() : ViewModel() {

    val dogBreedLiveData = MutableLiveData<List<String>>()

    fun getAllDogBreeds() {
        viewModelScope.launch(Dispatchers.IO) {
            val dogRepository = DogRepository()
            val dogBreeds = dogRepository.getDogBreed()?.message
            val breedsList = mutableListOf<String>()

            dogBreeds?.let {
                if (it.affenpinscher != null) breedsList.add("affenpinscher")
                if (it.hound != null) breedsList.add("hound")
                if (it.boxer != null) breedsList.add("boxer")
                if (it.bulldog != null) breedsList.add("bulldog")
                if (it.husky != null) breedsList.add("husky")
            }
            dogBreedLiveData.postValue(breedsList)
        }

    }


}