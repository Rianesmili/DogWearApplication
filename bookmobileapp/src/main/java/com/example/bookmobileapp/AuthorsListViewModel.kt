package com.example.bookmobileapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthorsListViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    val mobileToPhoneCommunicator: MobileToWearComunicator
) : ViewModel() {

    val bookAuthorsLiveData = MutableLiveData<List<String>>()

    fun getAllBookAuthors() {
        viewModelScope.launch(Dispatchers.IO) {
            val bookAuthors = bookRepository.getAuthorsAndSaveThemLocally()
            bookAuthors?.authors?.take(5)
            bookAuthorsLiveData.postValue(bookAuthors?.authors?.take(5) ?: emptyList())
        }
    }



}