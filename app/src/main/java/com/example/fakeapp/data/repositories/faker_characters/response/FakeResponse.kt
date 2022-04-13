package com.example.fakeapp.data.repositories.faker_characters.response

import com.example.fakeapp.data.network.ResponseModel
import com.google.gson.annotations.SerializedName


class FakeResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: List<FakeUserResponse>,
    @SerializedName("status")
    val status: String,
    @SerializedName("total")
    val total: Int
) : ResponseModel<FakeResponseModel> {
    override fun toModel() =
        FakeResponseModel(code,
            data.map { it.toModel()},
            status,
            total
        )
}




