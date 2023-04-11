package com.example.myapplication

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.rememberScalingLazyListState
import com.example.myapplication.Screen.BASE_IMAGE_BY_BREED_ROUTE

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DogBreedsView(dogBreedsViewModel: DogBreedsViewModel = hiltViewModel(), navController: NavHostController) {

    val dogBreeds by dogBreedsViewModel.dogBreedLiveData.observeAsState(emptyList<String>())
    dogBreedsViewModel.getAllDogBreeds()

    Surface(modifier = Modifier.fillMaxSize()) {

        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = 10.dp,
                start = 10.dp,
                end = 10.dp,
                bottom = 40.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = rememberScalingLazyListState() //remember and by
        ) {
            items(dogBreeds.size) { index ->
                Chip(
                    modifier = Modifier
                        .width(140.dp)
                        .padding(top = 10.dp),
                    onClick = {
                        val selectedBreed = dogBreeds[index]
                        navController.navigate(
                            route = "$BASE_IMAGE_BY_BREED_ROUTE$selectedBreed"
                        )
                    }
                ) {
                    Text(
                        text = dogBreeds[index],
                        modifier = Modifier
                            .width(140.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }
}
