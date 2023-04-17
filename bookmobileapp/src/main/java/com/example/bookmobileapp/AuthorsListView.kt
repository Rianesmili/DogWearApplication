package com.example.bookmobileapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
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
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(authorList.size) { index ->
                Card(
                    modifier = Modifier
                        .width(200.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .clickable {
                            val authorName = authorList[index]
                            authorsListViewModel.viewModelScope.launch {
                                authorsListViewModel.mobileToPhoneCommunicator.sendAuthorToWear(
                                    authorName
                                )
                            }
                            println(authorList[index])
                        },
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),


                    ) {
                    Text(
                        text = authorList[index],
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .width(140.dp)
                            .padding(all = 40.dp),
                        textAlign = TextAlign.Center,
                        //color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyLarge

                    )
                }

            }

        }
    }
}

