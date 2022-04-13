package com.example.fakeapp.data.repositories.fake_user

import com.example.fakeapp.data.network.RetrofitApiFactory
import com.example.fakeapp.data.network.FakeResult
import com.example.fakeapp.data.repositories.faker_characters.response.FakeResponseModel

class FakeRepository(
    retrofitApiFactory: RetrofitApiFactory
) {
    private val apiService = retrofitApiFactory.fakeApiService

    suspend fun getFakeUsers(): FakeResult<FakeResponseModel> {
        val response = apiService.getFakeUsers(5, "male")
        return response.toResult()
    }

}