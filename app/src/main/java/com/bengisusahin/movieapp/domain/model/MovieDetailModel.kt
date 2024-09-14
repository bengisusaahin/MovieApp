package com.bengisusahin.movieapp.domain.model

import com.bengisusahin.movieapp.data.remote.dto.Rating

data class MovieDetailModel(
    val Actors: String,
    val Awards: String,
    val Country: String,
    val Director: String,
    val Genre: String,
    val Language: String,
    val Poster: String,
    val Released: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbRating: String
)