package com.example.bookmobileapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.bookmobileapp.Screen.SEND_RANDOM_AUTHOR_ACTION_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var broadcastReceiver: BroadcastReceiver
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            AuthorsListView()
        }

    }

    override fun onStart() {
        super.onStart()
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == SEND_RANDOM_AUTHOR_ACTION_KEY) {
                    viewModel.sendRandomAuthorToWear()
                }
            }
        }

        registerReceiver(broadcastReceiver, IntentFilter(SEND_RANDOM_AUTHOR_ACTION_KEY))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(broadcastReceiver)

    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }
}
