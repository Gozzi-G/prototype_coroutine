package com.example.moviesdbapp.data.repositories.movie

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    /**
     * Movies List
     */
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: Int,
    )

}