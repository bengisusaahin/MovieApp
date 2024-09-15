package com.bengisusahin.movieapp.presentation.movies

import com.bengisusahin.movieapp.domain.model.MovieModel

data class MovieState (
    val isLoading: Boolean = false,
    val movies: List<MovieModel> = emptyList(),
    val error: String = "",
    val search: String = "batman"
)
