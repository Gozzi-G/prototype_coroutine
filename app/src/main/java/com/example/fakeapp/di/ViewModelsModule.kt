package com.example.fakeapp.di

import com.example.fakeapp.ui.movies.movies.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        MovieViewModel(
            fakeRepository = get()
        )
    }
}