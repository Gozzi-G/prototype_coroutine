package com.example.moviesdbapp.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TmdbResult<out Model> private constructor(
    private val dataResult: Model?,
    private val errorResult: Error?,
) {

    companion object {
        fun <Model, Response> fromResponse(response: TmdbResponse<Response>): TmdbResult<Model> {
            when (response) {
                is TmdbResponse.Success -> {
                    var exceptionMessage = ""

                    val dataResult = try {
                        (response.data as ResponseModel<Model>).toModel()
                    } catch (e: ClassCastException) {
                        exceptionMessage = e.message ?: ""
                        null
                    }

                    if (dataResult == null) {
                        val errorResult = Error(
                                statusCode = -1,
                                statusMessage = exceptionMessage,
                                isNetworkError = false,
                        )
                        return TmdbResult(dataResult = null, errorResult = errorResult)
                    } else {
                        return TmdbResult(dataResult = dataResult, errorResult = null)
                    }
                }
                is TmdbResponse.Failure -> {
                    return TmdbResult(
                        dataResult = null,
                        errorResult = Error(
                            statusCode = response.statusCode,
                            statusMessage = response.statusMessage,
                            isNetworkError = response.isNetworkError,
                        )
                    )
                }
            }
        }
    }

    class Error(
            val statusCode: Int,
            val statusMessage: String,
            val isNetworkError: Boolean,
    )

    private var isMainThread = false

    val isSuccess: Boolean
        get() = errorResult == null

    val data: Model
        get() = dataResult!!

    val error: Error
        get() = errorResult!!

    suspend fun onError(fn: suspend (error: Error) -> Unit): TmdbResult<Model> {
        if (isSuccess.not()) {
            if (isMainThread) {
                withContext(Dispatchers.Main) {
                    fn.invoke(error)
                }
            } else {
                fn.invoke(error)
            }
        }
        return this
    }

    suspend fun onSuccess(fn: suspend (Model) -> Unit): TmdbResult<Model> {
        if (isSuccess) {
            if (isMainThread) {
                withContext(Dispatchers.Main) {
                    fn.invoke(data)
                }
            } else {
                fn.invoke(data)
            }
        }
        return this
    }

    fun useMainThread(): TmdbResult<Model> {
        isMainThread = true
        return this
    }

    fun useIOThread(): TmdbResult<Model> {
        isMainThread = false
        return this
    }

}