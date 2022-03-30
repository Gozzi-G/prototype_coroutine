package com.example.moviesdbapp.di

import com.example.moviesdbapp.ui.movies.movies.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        MovieViewModel(
            movieRepository = get()
        )
    }
}