package com.bengisusahin.movieapp.presentation.movieDetail

import com.bengisusahin.movieapp.domain.model.MovieDetailModel

data class MovieDetailState (
    val isLoading: Boolean = false,
    val movie: MovieDetailModel? = null,
    val error: String = ""
)