package com.example.bookmobileapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookmobileapp.Screen.SEND_AUTHOR_TO_WEAR_KEY
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthorsListView(authorsListViewModel: AuthorsListViewModel = hiltViewModel()) {

    val authorList by authorsListViewModel.bookAuthorsLiveData.observeAsState(emptyList<String>())
    authorsListViewModel.getAllBookAuthors()

    Surface(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = 10.dp,
                start = 10.dp,
                end = 10.dp,
                bottom = 40.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(authorList.size) { index ->
                Row(
                    modifier = Modifier
                        .width(140.dp)
                        .padding(top = 10.dp)
                        .clickable {
                            val authorName = authorList[index]

                            authorsListViewModel.viewModelScope.launch {
                                authorsListViewModel.mobileToPhoneCommunicator.sendMessageToWear(
                                    SEND_AUTHOR_TO_WEAR_KEY,
                                    authorName.toByteArray()
                                )

                            }
                            println(authorList[index])
                        }

                ) {
                    Text(
                        text = authorList[index],
                        modifier = androidx.compose.ui.Modifier
                            .width(140.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }

        }
    }
}
