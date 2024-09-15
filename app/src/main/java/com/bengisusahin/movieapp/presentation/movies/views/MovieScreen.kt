package com.bengisusahin.movieapp.presentation.movies.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bengisusahin.movieapp.presentation.movies.MoviesEvent
import com.bengisusahin.movieapp.presentation.movies.MoviesViewModel

@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)){
        Column {
            SearchBar(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                hint = "Batman",
                onSearch = {
                    viewModel.onEvent(MoviesEvent.Search(it))
                }
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.movies){ movie ->
                    Text(text = movie.Title,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White)

                }
            }
        }
    }
}

