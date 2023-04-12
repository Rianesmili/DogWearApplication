package com.example.bookmobileapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val authorLiveData = MutableLiveData<String>("abc")

    fun updateAuthor (author: String){
        authorLiveData.value = author
    }
}