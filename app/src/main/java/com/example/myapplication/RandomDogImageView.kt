package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun RandomImageView(
    randomDogImageViewModel: RandomDogImageViewModel = hiltViewModel(),
    navController: NavController
) {

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
            Button(
                onClick = {
                    randomDogImageViewModel.getRondomDogImageUrl()
                },
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(text = "Another One", color = Color.White)
            }
        }
    }

}