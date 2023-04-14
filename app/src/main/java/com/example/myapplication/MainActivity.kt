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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.navigation.NavHostController
import com.example.myapplication.Screen.SEND_AUTHOR_INTENT_ACTION_KEY
import com.example.myapplication.Screen.SEND_AUTHOR_INTENT_PUT_EXTRA_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavHostController
    lateinit var broadcastReceiver: BroadcastReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            hello()
        }

    }
/**

    @Composable
    fun App2() {
        val authorsListViewModel: AuthorsListViewModel = viewModel()
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, intent: Intent?) {
                if (intent?.action == SEND_AUTHOR_INTENT_ACTION_KEY) {
                    val author = intent.getStringExtra(SEND_AUTHOR_INTENT_PUT_EXTRA_KEY)
                    authorsListViewModel.updateAuthor(author.orEmpty())
                }
            }
        }
        registerReceiver(broadcastReceiver, IntentFilter(SEND_AUTHOR_INTENT_ACTION_KEY))
        AuthorsListView()
    }
 */

    @Composable
    fun hello(){
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            val authorsListViewModel: AuthorsListViewModel = viewModel()
            broadcastReceiver = object : BroadcastReceiver() {
                override fun onReceive(p0: Context?, intent: Intent?) {
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
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = author.orEmpty(),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver) // Relacher de la mémoir le Receiver important pour les fuite memoir memory leak
    }
}