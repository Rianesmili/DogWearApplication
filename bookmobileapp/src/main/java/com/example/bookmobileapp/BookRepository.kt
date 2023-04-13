package com.example.bookmobileapp

import com.example.bookmobileapp.webService.BookApi
import com.example.bookmobileapp.webService.BookAuthors

class BookRepository (private val bookApi: BookApi) {

    fun getAuthors(): BookAuthors? = bookApi.getAllAuthors().execute().body()

}