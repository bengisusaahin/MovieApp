package com.bengisusahin.movieapp.domain.repository

import com.bengisusahin.movieapp.data.remote.dto.MovieDetailDto
import com.bengisusahin.movieapp.data.remote.dto.MoviesDto

interface MovieRepository {

    suspend fun getMovies(search: String): MoviesDto
    suspend fun getMovieDetail(imdbId : String) : MovieDetailDto
}