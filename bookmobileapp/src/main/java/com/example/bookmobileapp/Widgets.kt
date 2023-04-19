package com.example.bookmobileapp

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.widget.RemoteViews
import com.example.bookmobileapp.Screen.SEND_RANDOM_AUTHOR_ACTION_KEY
import javax.inject.Inject

/**
 * Implementation of App Widget functionality.
 */
class Widgets : AppWidgetProvider() {

    lateinit var broadcastReceiver: BroadcastReceiver

    @Inject lateinit var bookRepository: BookRepository

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        Log.d("widgets", "onUpdate widget entrance")

        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
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

        context.applicationContext.registerReceiver(broadcastReceiver, IntentFilter(SEND_RANDOM_AUTHOR_ACTION_KEY))
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

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = context.getString(R.string.appwidget_text)

    // Construct the RemoteViews object

    val views = RemoteViews(context.packageName, R.layout.widgets)


    views.setTextViewText(R.id.appwidget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)




}