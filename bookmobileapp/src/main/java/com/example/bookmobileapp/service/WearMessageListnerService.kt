    package com.example.bookmobileapp.service


    import android.content.Intent
    import com.example.bookmobileapp.Screen.SEND_AUTHOR_INTENT_ACTION_KEY
    import com.example.bookmobileapp.Screen.SEND_AUTHOR_INTENT_PUT_EXTRA_KEY
    import com.example.bookmobileapp.Screen.SEND_AUTHOR_TO_WEAR_KEY
    import com.example.bookmobileapp.Screen.SEND_RANDOM_AUTHOR_ACTION_KEY
    import com.example.bookmobileapp.Screen.SEND_RANDOM_AUTHOR_REQUEST
    import com.google.android.gms.wearable.MessageEvent
    import com.google.android.gms.wearable.WearableListenerService

    class MobileMessageService : WearableListenerService() {

        override fun onMessageReceived(messageEvent: MessageEvent) {
            super.onMessageReceived(messageEvent)

            println("Je suis dans onMessage Received ${messageEvent.path}")

            if (messageEvent.path == SEND_AUTHOR_TO_WEAR_KEY) {
                val authorName = String(messageEvent.data)
                sendBroadcast(
                    Intent().apply {
                        action = SEND_AUTHOR_INTENT_ACTION_KEY
                        putExtra(SEND_AUTHOR_INTENT_PUT_EXTRA_KEY, authorName)
                    }
                )
            }
            else if (messageEvent.path == SEND_RANDOM_AUTHOR_REQUEST ){
                sendBroadcast(
                    Intent().apply {
                        action = SEND_RANDOM_AUTHOR_ACTION_KEY
                    }
                )
            }
        }
    }