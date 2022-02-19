package com.example.moviesdbapp.data.network


sealed class TmdbResponse<out Response> {

    class Success<Response>(val data: Response?) : TmdbResponse<Response>()

    class Failure(
            val statusCode: Int,
            val statusMessage: String,
            val isNetworkError: Boolean,
    ) : TmdbResponse<Nothing>()

    @Suppress("UNCHECKED_CAST")
    fun <Model> toResult(): TmdbResult<Model> {
        return TmdbResult.fromResponse(this)
    }

}