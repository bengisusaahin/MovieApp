package com.bengisusahin.movieapp.data.repository

import com.bengisusahin.movieapp.data.remote.MovieAPI
import com.bengisusahin.movieapp.data.remote.dto.MovieDetailDto
import com.bengisusahin.movieapp.data.remote.dto.MoviesDto
import com.bengisusahin.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieAPI
): MovieRepository {
    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovies(search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieDetail(imdbId)
    }
}