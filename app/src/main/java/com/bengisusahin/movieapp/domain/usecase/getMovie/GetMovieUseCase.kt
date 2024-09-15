package com.bengisusahin.movieapp.domain.usecase.getMovie

import coil.network.HttpException
import com.bengisusahin.movieapp.data.remote.mapper.toMovieList
import com.bengisusahin.movieapp.domain.model.MovieModel
import com.bengisusahin.movieapp.domain.repository.MovieRepository
import com.bengisusahin.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    fun executeGetMovies(search: String) : Flow<Resource<List<MovieModel>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getMovies(search)
            if (movieList.Response == "True"){
                emit(Resource.Success(movieList.toMovieList()))
            }else{
                emit(Resource.Error("Movie not found"))
            }
        } catch (e: IOError){
            emit(Resource.Error("No internet connection"))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Error"))
        }
    }
}