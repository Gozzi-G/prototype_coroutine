package com.example.moviesdbapp.di

import com.example.moviesdbapp.data.network.RetrofitApiFactory
import org.koin.dsl.module

val appModule = module {
    single {
        RetrofitApiFactory(
            appContext = get()
        )
    }
}