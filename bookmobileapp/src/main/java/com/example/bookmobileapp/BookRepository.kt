package com.example.bookmobileapp

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import com.example.bookmobileapp.Screen.GET_LIST_AUTHOR_FROM_SHARED_PREFERENCES_KEY
import com.example.bookmobileapp.Screen.SHARED_PREFERENCES_NAME_KEY
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

        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_NAME_KEY, Context.MODE_PRIVATE)

        with (sharedPref.edit()) {
            putString(GET_LIST_AUTHOR_FROM_SHARED_PREFERENCES_KEY, Gson().toJson(authors))
            apply()
        }
        val listeAuthorsSharedPreferences = sharedPref.getString(GET_LIST_AUTHOR_FROM_SHARED_PREFERENCES_KEY, "")
    }

    fun getAuthorsLocally(): List<String> {

        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_NAME_KEY, Context.MODE_PRIVATE) ?: return emptyList()

        val listeAuthorsSharedPreferences = sharedPref.getString(GET_LIST_AUTHOR_FROM_SHARED_PREFERENCES_KEY, "")

        return if (listeAuthorsSharedPreferences.isNullOrEmpty()) {
            emptyList()
        } else {
            val listType = object : TypeToken<List<String>>() {}.type
            Gson().fromJson(listeAuthorsSharedPreferences, listType)
        }
    }

}