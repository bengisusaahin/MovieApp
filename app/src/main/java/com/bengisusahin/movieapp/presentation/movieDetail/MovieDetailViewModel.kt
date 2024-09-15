package com.bengisusahin.movieapp.presentation.movieDetail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bengisusahin.movieapp.domain.usecase.getMovieDetail.GetMovieDetailUseCase
import com.bengisusahin.movieapp.util.Constants.IMDB_ID
import com.bengisusahin.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf<MovieDetailState>(MovieDetailState())
    val state: State<MovieDetailState> = _state

    init {
        savedStateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetail(it)
        }
    }

    private fun getMovieDetail(imdbId: String) {
        getMovieDetailUseCase.executeGetMovieDetail(imdbId).onEach {
            when (it) {
                is Resource.Success -> {
                    Log.d( "getMovieDetail: ", "Success = ${it.data}")
                    _state.value = _state.value.copy(movie = it.data, isLoading = false)
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = it.message ?: "Error!", isLoading = false)
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}