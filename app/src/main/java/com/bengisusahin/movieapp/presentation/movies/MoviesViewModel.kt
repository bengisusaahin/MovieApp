package com.bengisusahin.movieapp.presentation.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bengisusahin.movieapp.domain.usecase.getMovie.GetMovieUseCase
import com.bengisusahin.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel(){
    private val _state = mutableStateOf<MovieState>(MovieState())
    val state : State<MovieState> = _state

    private var job : Job? = null

    init {
        getMovies(_state.value.search)
    }

    private fun getMovies(search: String){
        // bir arama yapıldıysa önceki aramayı iptal et gibi
        job?.cancel()
        job = getMovieUseCase.executeGetMovies(search).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(movies = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = it.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent (event: MoviesEvent){
        when(event){
            is MoviesEvent.Search -> {
                getMovies(event.searchString)
            }
        }
    }
}