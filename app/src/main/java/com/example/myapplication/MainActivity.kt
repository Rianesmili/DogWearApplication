package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
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
            App2()
        }

    }

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
    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver) // Relacher de la mémoir le Receiver important pour les fuite memoir memory leak
    }
}