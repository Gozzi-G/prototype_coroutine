package com.example.fakeapp.data.network


sealed class FakeResponse<out Response> {

    class Success<Response>(val data: Response?) : FakeResponse<Response>()

    class Failure(
            val statusCode: Int,
            val statusMessage: String,
            val isNetworkError: Boolean,
    ) : FakeResponse<Nothing>()

    @Suppress("UNCHECKED_CAST")
    fun <Model> toResult(): FakeResult<Model> {
        return FakeResult.fromResponse(this)
    }

}