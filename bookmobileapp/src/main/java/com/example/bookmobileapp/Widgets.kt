package com.example.bookmobileapp

import android.annotation.SuppressLint
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.widget.RemoteViews
import com.example.bookmobileapp.Screen.SEND_RANDOM_AUTHOR_ACTION_KEY
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Implementation of App Widget functionality.
 */

@AndroidEntryPoint
class Widgets : AppWidgetProvider() {

    lateinit var broadcastReceiver: BroadcastReceiver

    @Inject
    lateinit var bookRepository: BookRepository


    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        Log.d("widgets", "onUpdate widget entrance")

        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, bookRepository)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == SEND_RANDOM_AUTHOR_ACTION_KEY) {
                    println("condition ok")
                }
            }
        }

        context.applicationContext.registerReceiver(
            broadcastReceiver,
            IntentFilter(SEND_RANDOM_AUTHOR_ACTION_KEY)
        )
        Log.d("widgets", "onEnabled widget entrance")
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
        Log.d("widgets", "onDisabled widget entrance")
    }


    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        //This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Log.d("widgets", "onReceive widget ${intent}")


    }
}

@SuppressLint("RemoteViewLayout")
internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    bookRepository: BookRepository
) {

    val authors = bookRepository.getAuthorsLocally()


    println(authors[0])

    // Construct the RemoteViews object

    val views = RemoteViews(context.packageName, R.layout.widgets)

    views.setTextViewText(R.id.main_item_1, authors.getOrNull(0) ?: "")
    views.setTextViewText(R.id.main_item_2, authors.getOrNull(1) ?: "")
    views.setTextViewText(R.id.main_item_3, authors.getOrNull(2) ?: "")


    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}