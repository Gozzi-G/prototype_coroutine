package com.example.fakeapp.data.repositories.fake_user

import com.example.fakeapp.data.repositories.faker_characters.response.FakeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FakeApiService {

    @GET("users")
    suspend fun getFakeUsers(
        @Query("quantity") quantity: Int,
        @Query("gender") gender: String,
    ): com.example.fakeapp.data.network.FakeResponse<FakeResponse>

}