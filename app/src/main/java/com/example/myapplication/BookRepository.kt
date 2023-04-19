package com.example.myapplication

import com.example.webService.BookApi
import com.example.webService.BookAuthors

class BookRepository (private val bookApi: BookApi) {

    fun getAuthors(): BookAuthors? = bookApi.getAllAuthors().execute().body()

}