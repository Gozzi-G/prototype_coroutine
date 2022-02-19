package com.example.moviesdbapp.data.repositories.movie

import com.example.moviesdbapp.data.network.RetrofitApiFactory

class MovieRepository(
    retrofitApiFactory: RetrofitApiFactory
) {
    private val apiService = retrofitApiFactory.movieApiService
}