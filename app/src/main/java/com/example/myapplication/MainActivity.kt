package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.Screen.SEND_AUTHOR_INTENT_ACTION_KEY
import com.example.myapplication.Screen.SEND_AUTHOR_INTENT_PUT_EXTRA_KEY
import com.example.webService.DogImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var broadcastReceiver: BroadcastReceiver
    val wearToPhoneCommunicator = WearToPhoneCommunicator(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisplaySelectedAthorView(wearToPhoneCommunicator)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

    @Composable
    fun DisplaySelectedAthorView(wearToPhoneCommunicator: WearToPhoneCommunicator) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val authorsListViewModel: AuthorsListViewModel = viewModel()
            broadcastReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    if (intent?.action == SEND_AUTHOR_INTENT_ACTION_KEY) {
                        val author = intent.getStringExtra(SEND_AUTHOR_INTENT_PUT_EXTRA_KEY)
                        authorsListViewModel.updateAuthor(author.orEmpty())
                    }
                }
            }
            registerReceiver(broadcastReceiver, IntentFilter(SEND_AUTHOR_INTENT_ACTION_KEY))
            val author by authorsListViewModel.authorLiveData.observeAsState()
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = author.orEmpty(),
                    textAlign = TextAlign.Center,
                )
                Button(onClick = {
                    authorsListViewModel.askNewRandomAuthor(wearToPhoneCommunicator)
                }) {
                    Text("Rafraichir")
                }
            }
        }
    }
}
