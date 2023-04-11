package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.example.myapplication.DogBreedsFragment.Companion.BREED_KEY

class ImageByBreedFragment : Fragment() {

    private lateinit var ImageByBreedViewModel: ImageByBreedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImageByBreedViewModel = ViewModelProvider(this )[ImageByBreedViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MyApp { changeFragment() }
            }
        }
    }
    @Composable
    fun MyApp(changeFragment: () -> Unit) {

        val imageUrl by ImageByBreedViewModel.dogImageLiveData.observeAsState("")

        requireArguments().getString(BREED_KEY)?.also { breed ->
            ImageByBreedViewModel.getRandomImageByBreed(breed)
        }

        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )
                Button(
                    onClick = changeFragment,
                    colors = ButtonDefaults.buttonColors(Color.Blue),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text("Show rnd img", color = Color.White)
                }
            }
        }
    }

    private fun changeFragment() {
        findNavController().navigate(R.id.action_secondFragment_to_imageFragment)
    }
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ImageByBreedFragment().MyApp {}
    }


}