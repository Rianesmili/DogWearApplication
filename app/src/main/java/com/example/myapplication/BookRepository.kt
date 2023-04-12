package com.example.myapplication

import com.example.webService.BookAuthors
import com.example.webService.BookApi
import javax.inject.Inject

class BookRepository (private val bookApi: BookApi) {

    fun getAuthors(): BookAuthors? = bookApi.getAllAuthors().execute().body()

}