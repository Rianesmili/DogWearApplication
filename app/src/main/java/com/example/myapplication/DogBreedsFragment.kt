package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.rememberScalingLazyListState

class DogBreedsFragment : Fragment() {

    companion object {
        const val BREED_KEY = "breed"
    }

    private lateinit var dogBreedsViewModel: DogBreedsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dogBreedsViewModel = ViewModelProvider(this)[DogBreedsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                DogBreedsList()
            }
        }
    }

    @Composable
    fun MyApp(changeFragment: () -> Unit) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Test first view",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Button(
                    onClick = changeFragment,
                    colors = ButtonDefaults.buttonColors(Color.Blue),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text("Click me", color = Color.White)
                }
            }
        }
    }

    private fun openImageByBreed(breed: String) {
        findNavController().navigate(
            R.id.action_firstFragment_to_secondFragment,
            bundleOf(BREED_KEY to breed)
        )
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun DogBreedsList() {
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
                            openImageByBreed(selectedBreed)
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

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        DogBreedsList()
    }
}