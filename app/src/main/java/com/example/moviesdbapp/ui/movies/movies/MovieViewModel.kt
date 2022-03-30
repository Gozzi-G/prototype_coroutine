package com.example.moviesdbapp.ui.movies.movies

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.moviesdbapp.base.BaseViewModel
import com.example.moviesdbapp.data.repositories.movie.MovieRepository
import kotlinx.coroutines.*

class MovieViewModel(
    private val movieRepository: MovieRepository
): BaseViewModel() {

    fun onViewCreated() {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launchOnIO {
            movieRepository.getMovies(page = 1)
                .onSuccess {
                    Log.d("MovieViewModel", "onSuccess: ${it}")
                }
                .onError {
                    Log.d("MovieViewModel", "onError: ${it.statusMessage}")
                }
        }
    }

}

fun CoroutineScope.launchOnIO(
    block: suspend CoroutineScope.() -> Unit
): Job {
    return launch(Dispatchers.IO, CoroutineStart.DEFAULT, block)
}