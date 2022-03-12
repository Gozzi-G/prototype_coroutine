package com.example.moviesdbapp.data.repositories.movie

import com.example.moviesdbapp.Const
import com.example.moviesdbapp.data.network.RetrofitApiFactory
import com.example.moviesdbapp.data.network.TmdbResult

class MovieRepository(
    retrofitApiFactory: RetrofitApiFactory
) {
    private val apiService = retrofitApiFactory.movieApiService
    private val apiKey = Const.apiKey
    private val language = Const.language

    suspend fun getMovies(page: Int): TmdbResult<MoviesList> {
        val response = apiService.getPopularMovies(apiKey, page, language)
        return response.toResult()
    }
}