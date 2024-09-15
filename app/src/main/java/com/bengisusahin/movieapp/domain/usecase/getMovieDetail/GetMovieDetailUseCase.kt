package com.bengisusahin.movieapp.domain.usecase.getMovieDetail

import coil.network.HttpException
import com.bengisusahin.movieapp.data.remote.mapper.toMovieDetail
import com.bengisusahin.movieapp.domain.model.MovieDetailModel
import com.bengisusahin.movieapp.domain.repository.MovieRepository
import com.bengisusahin.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
){
    fun executeGetMovieDetail(imdbId: String) : Flow<Resource<MovieDetailModel>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetail(imdbId)
            emit(Resource.Success(movieDetail.toMovieDetail()))
        } catch (e: IOError){
            emit(Resource.Error("No internet connection"))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Error"))
        }
    }
}