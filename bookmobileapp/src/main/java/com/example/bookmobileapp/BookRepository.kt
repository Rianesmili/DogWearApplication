package com.example.bookmobileapp

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import com.example.bookmobileapp.webService.BookApi
import com.example.bookmobileapp.webService.BookAuthors
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BookRepository (private val bookApi: BookApi, private val context: Context) {

    fun getAuthorsAndSaveThemLocally(): BookAuthors? {
        val authors = getAuthors()
        saveAuthors(authors?.authors ?: emptyList())
        return authors
    }
    private fun getAuthors(): BookAuthors? = bookApi.getAllAuthors().execute().body()

    fun saveAuthors(authors: List<String>){

        val sharedPref = context.getSharedPreferences("shared_pref_key", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString("liste_authors", Gson().toJson(authors))
            apply()
        }
        val listeAuthorsSharedPreferences = sharedPref.getString("liste_authors", "")
        println("saveAuthor fun in bookRepository: $listeAuthorsSharedPreferences")
    }

    fun getAuthorsLocally(): List<String> {
        val sharedPref = context.getSharedPreferences("shared_pref_key", Context.MODE_PRIVATE) ?: return emptyList()
        val listeAuthorsSharedPreferences = sharedPref.getString("liste_authors", "")

        return if (listeAuthorsSharedPreferences.isNullOrEmpty()) {
            emptyList()
        } else {
            val listType = object : TypeToken<List<String>>() {}.type
            Gson().fromJson(listeAuthorsSharedPreferences, listType)
        }
    }

}