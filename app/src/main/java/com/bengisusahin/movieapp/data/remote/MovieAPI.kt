package com.bengisusahin.movieapp.data.remote

import com.bengisusahin.movieapp.data.remote.dto.MovieDetailDto
import com.bengisusahin.movieapp.data.remote.dto.MoviesDto
import com.bengisusahin.movieapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    //https://www.omdbapi.com/?apikey=c7794418&s=batman
    //https://www.omdbapi.com/?apikey=c7794418&i=tt1285016

    // direkt parametreye geçtiği extension kısmı omadığı için . koyabiliyoruz
    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString: String,
        @Query("apikey") apiKey: String = API_KEY
    ): MoviesDto

    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String = API_KEY
    ) : MovieDetailDto
}