package com.example.fakeapp.di

import com.example.fakeapp.data.network.RetrofitApiFactory
import org.koin.dsl.module

val appModule = module {
    single {
        RetrofitApiFactory(
            appContext = get()
        )
    }
}