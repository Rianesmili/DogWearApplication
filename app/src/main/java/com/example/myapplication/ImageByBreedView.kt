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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.Screen.RANDOM_DOG_IMAGE_ROUTE


@Composable
fun ImageByBreedView(imageByBreedViewModel: ImageByBreedViewModel = hiltViewModel(), navController: NavController, breed: String) {

    val imageByBreed by imageByBreedViewModel.dogImageLiveData.observeAsState()
    imageByBreedViewModel.getRandomImageByBreed(breed)

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = imageByBreed,
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Button(
                onClick = {
                          navController.navigate(route = "$RANDOM_DOG_IMAGE_ROUTE")
                },
                colors = ButtonDefaults.buttonColors(Color.Blue),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text("Show rondom dog image", color = Color.White)

            }
        }
    }

}