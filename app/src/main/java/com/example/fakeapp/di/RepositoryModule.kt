package com.example.fakeapp.di

import com.example.fakeapp.data.repositories.fake_user.FakeRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single {
        FakeRepository(
            retrofitApiFactory = get()
        )
    }
}