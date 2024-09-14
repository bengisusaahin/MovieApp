package com.bengisusahin.movieapp.data.remote.mapper

import com.bengisusahin.movieapp.data.remote.dto.MovieDetailDto
import com.bengisusahin.movieapp.data.remote.dto.MoviesDto
import com.bengisusahin.movieapp.domain.model.MovieDetailModel
import com.bengisusahin.movieapp.domain.model.MovieModel

fun MoviesDto.toMovieList(): List<MovieModel> {
    return Search.map { search -> MovieModel(search.Poster, search.Title, search.Year, search.imdbID) }
}

fun MovieDetailDto.toMovieDetail(): MovieDetailModel{
    return MovieDetailModel(Actors, Awards, Country, Director, Genre, Language, Poster, Released, Title, Type, Year, imdbRating)
}