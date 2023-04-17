package com.example.myapplication.Service

import android.content.Intent
import com.example.myapplication.Screen.SEND_AUTHOR_INTENT_ACTION_KEY
import com.example.myapplication.Screen.SEND_AUTHOR_INTENT_PUT_EXTRA_KEY
import com.example.myapplication.Screen.SEND_AUTHOR_TO_WEAR_KEY
import com.google.android.gms.wearable.MessageEvent
import com.google.android.gms.wearable.WearableListenerService

class MobileMessageListnerService : WearableListenerService() {

    //Est executée en permanance, et attend une action particuliére
    //Son role c'est la recepation de message et pas l'envoie de message, ce service vas servir a la reception depuis le telphone
    override fun onMessageReceived(messageEvent: MessageEvent) {
        super.onMessageReceived(messageEvent)
        if (messageEvent.path == SEND_AUTHOR_TO_WEAR_KEY) {
            val authorName = String(messageEvent.data)
            sendBroadcast(
                Intent().apply {
                    action = SEND_AUTHOR_INTENT_ACTION_KEY
                    putExtra(SEND_AUTHOR_INTENT_PUT_EXTRA_KEY, authorName)
                }
            )
        }
    }
}