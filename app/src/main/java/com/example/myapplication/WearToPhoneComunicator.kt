package com.example.myapplication

import android.content.Context
import com.google.android.gms.wearable.Node
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.suspendCoroutine
import kotlin.coroutines.resume

class WearToPhoneCommunicator(private val context: Context) {

    // path "ask_send_random_author"
    suspend fun sendMessageToMobile(path: String, data: ByteArray) {
        withContext(Dispatchers.IO) {
            val nodes = suspendCoroutine<List<Node>> { continuation ->
                Wearable.getNodeClient(context).connectedNodes.addOnSuccessListener { nodes ->
                    println("Hello from node client success listener $nodes")
                    continuation.resume(nodes)
                }
            }

            for (node in nodes) {
                Wearable.getMessageClient(context).sendMessage(node.id, path, data)
            }
        }
    }
}
