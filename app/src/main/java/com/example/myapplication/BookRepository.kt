package com.example.myapplication

import com.example.webService.Authors
import com.example.webService.BookApi

class BookRepository (private val bookApi: BookApi) {

    fun getAuthors() : Authors? {
        return bookApi.getAllAuthors().execute().body()
    }

}