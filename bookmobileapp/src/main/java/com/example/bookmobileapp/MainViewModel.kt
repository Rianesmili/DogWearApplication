package com.example.bookmobileapp

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    val mobileToWearComunicator: MobileToWearComunicator,
) : ViewModel() {

    fun sendRandomAuthorToWear() {
        viewModelScope.launch(Dispatchers.IO) {
            val bookAuthors = bookRepository.getAuthorsAndSaveThemLocally()
            val author = bookAuthors?.authors?.random().orEmpty()
            mobileToWearComunicator.sendAuthorToWear(author)
            println("Auteur envoyé depuis le telephone: $author")
        }
    }

    fun sendAuthorsLocally(){
        viewModelScope.launch(Dispatchers.IO) {
            println("here is the body of json ${bookRepository.getAuthorsLocally()}")
        }
    }

}