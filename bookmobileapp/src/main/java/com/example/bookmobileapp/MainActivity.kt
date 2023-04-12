package com.example.bookmobileapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.bookmobileapp.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    val mainViewModel = MainViewModel()

    lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, intent: Intent?) {
                if (intent?.action == "selected_author") {
                    val author = intent.getStringExtra("selected_author_key")
                    mainViewModel.updateAuthor(author.orEmpty())
                }
            }
        }
        registerReceiver(broadcastReceiver, IntentFilter("selected_author"))


        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val author by mainViewModel.authorLiveData.observeAsState()
                    Text(
                        text = author.orEmpty()
                    )

                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver) // Relacher de la mémoir le Receiver important pour les fuite memoir memory leak
    }
}
