package com.example.moviesdbapp.data.network

import android.content.Context
import com.example.moviesdbapp.data.repositories.movie.MovieApiService

class RetrofitApiFactory(
    appContext: Context
) : RetrofitHolder(
    appContext = appContext
) {
    val movieApiService by lazy { createService(MovieApiService::class.java) }
}