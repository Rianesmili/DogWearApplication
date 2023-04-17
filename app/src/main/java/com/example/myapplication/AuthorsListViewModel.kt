package com.example.myapplication

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
    val wearToPhoneCommunicator: WearToPhoneCommunicator,
) : ViewModel() {
    val bookAuthorsLiveData = MutableLiveData<List<String>>()

    val authorLiveData = MutableLiveData<String>("Bonjour TGV InOUI Pro")

    fun getAllBookAuthors() {
        viewModelScope.launch(Dispatchers.IO) {
            val bookAuthors = bookRepository.getAuthors()

            bookAuthors?.authors?.take(5)
            bookAuthorsLiveData.postValue(bookAuthors?.authors?.take(5) ?: emptyList())

        }
    }
    fun askNewRandomAuthor(wearToPhoneCommunicator: WearToPhoneCommunicator){
        viewModelScope.launch(Dispatchers.IO) {
            wearToPhoneCommunicator.sendMessageToMobile("ask_send_random_author", byteArrayOf())
        }
    }

    fun updateAuthor (author: String){
        authorLiveData.value = author
    }




}