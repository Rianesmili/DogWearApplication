package com.example.bookmobileapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel@Inject constructor(
    private val bookRepository: BookRepository,
    val mobileToWearComunicator: MobileToWearComunicator,
) : ViewModel() {

    val authorLiveData = MutableLiveData<String>("abc")

    fun updateAuthor (author: String){
        authorLiveData.value = author
    }
}