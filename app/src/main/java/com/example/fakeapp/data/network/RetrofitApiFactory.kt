package com.example.fakeapp.data.network

import android.content.Context
import com.example.fakeapp.data.repositories.fake_user.FakeApiService

class RetrofitApiFactory(
    appContext: Context
) : RetrofitHolder(
    appContext = appContext
) {
    val fakeApiService by lazy { createService(FakeApiService::class.java) }
}