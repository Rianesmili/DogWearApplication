package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage


class RandomDogImageFragment : Fragment() {

    private lateinit var randomDogImageViewModel: RandomDogImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        randomDogImageViewModel = ViewModelProvider(this)[RandomDogImageViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MyApp {}
            }
        }
    }

    @Composable
    fun MyApp(function: () -> Unit) {
        val imageUrl by randomDogImageViewModel.urlLiveData.observeAsState("")
        randomDogImageViewModel.getRondomDogImageUrl()
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = imageUrl,
                    fontSize = 1.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )
            }
        }

    }
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        RandomDogImageFragment().MyApp {}
    }
}
