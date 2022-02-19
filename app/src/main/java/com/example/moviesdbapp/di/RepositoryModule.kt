package com.example.moviesdbapp.di

import com.example.moviesdbapp.data.repositories.movie.MovieRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single {
        MovieRepository(
            retrofitApiFactory = get()
        )
    }
}