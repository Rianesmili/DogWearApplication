package com.example.bookmobileapp.service


import android.content.Intent
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService

class MobileMessageService : WearableListenerService() {

    override fun onMessageReceived(messageEvent: MessageEvent) {
        super.onMessageReceived(messageEvent)
        if (messageEvent.path == "/selected_author") {
            val authorName = String(messageEvent.data)
            sendBroadcast(
                Intent().apply {
                    action = "selected_author"
                    putExtra("selected_author_key", authorName)
                }
            )
        }
    }
}