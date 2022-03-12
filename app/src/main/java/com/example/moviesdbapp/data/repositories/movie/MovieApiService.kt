package com.example.moviesdbapp.data.repositories.movie

import com.example.moviesdbapp.data.network.TmdbResponse
import com.example.moviesdbapp.data.network.TmdbResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    /**
     * Movies List
     */
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("language") language: String,
    ) : TmdbResult<MoviesListResponse>

}