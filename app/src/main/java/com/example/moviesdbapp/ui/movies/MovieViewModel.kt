package com.example.moviesdbapp.ui.movies

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
                    Log.d("MovieViewModel", "getMovies: ${it}")
                }
                .onError {
                    Log.d("MovieViewModel", "getMovies: ${it.statusMessage}")
                }
        }
    }

}

fun CoroutineScope.launchOnIO(
    block: suspend CoroutineScope.() -> Unit
): Job {
    return launch(Dispatchers.IO, CoroutineStart.DEFAULT, block)
}