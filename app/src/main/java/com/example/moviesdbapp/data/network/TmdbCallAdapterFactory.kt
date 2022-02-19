package com.example.moviesdbapp.data.network

import okhttp3.Request
import okio.Timeout
import org.json.JSONException
import org.json.JSONObject
import retrofit2.*
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


// Call adapter for suspending functions in Retrofit
// https://stackoverflow.com/a/57816819

class TmdbCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        return when (getRawType(returnType)) {
            Call::class.java -> {
                val callType = getParameterUpperBound(0, returnType as ParameterizedType)
                when (getRawType(callType)) {
                    TmdbResponse::class.java -> {
                        val resultType = getParameterUpperBound(0, callType as ParameterizedType)
                        ResultAdapter(resultType)
                    }
                    else -> null
                }
            }
            else -> null
        }
    }
}


class ResultAdapter(
    private val type: Type
) : CallAdapter<Type, Call<TmdbResponse<Type>>> {
    override fun responseType() = type
    override fun adapt(call: Call<Type>): Call<TmdbResponse<Type>> = ResultCall(call)
}


abstract class CallDelegate<TIn, TOut>(
    protected val proxy: Call<TIn>
) : Call<TOut> {
    override fun execute(): Response<TOut> = throw NotImplementedError()
    override fun enqueue(callback: Callback<TOut>) = enqueueImpl(callback)
    override fun clone(): Call<TOut> = cloneImpl()

    override fun cancel() = proxy.cancel()
    override fun request(): Request = proxy.request()
    override fun isExecuted() = proxy.isExecuted
    override fun isCanceled() = proxy.isCanceled

    abstract fun enqueueImpl(callback: Callback<TOut>)
    abstract fun cloneImpl(): Call<TOut>
}

class ResultCall<T>(proxy: Call<T>) : CallDelegate<T, TmdbResponse<T>>(proxy) {
    override fun enqueueImpl(callback: Callback<TmdbResponse<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {

                val tmdbResponse = if (response.isSuccessful) {
                    TmdbResponse.Success(response.body())

                } else {
                    val body = response.errorBody()!!.string()
                    try {
                        val obj = JSONObject(body)
                        val statusMessage = obj.optString("status_message")
                        val statusCode = obj.optInt("status_code", -1)

                        TmdbResponse.Failure(
                                statusCode = statusCode,
                                statusMessage = statusMessage,
                                isNetworkError = false,
                        )
                    } catch (e: JSONException) {
                        TmdbResponse.Failure(
                                statusCode = -1,
                                statusMessage = "",
                                isNetworkError = false,
                        )
                    }
                }

                callback.onResponse(this@ResultCall, Response.success(tmdbResponse))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val tmdbResponse = TmdbResponse.Failure(
                        statusCode = -1,
                        statusMessage = "",
                        isNetworkError = t is IOException
                )

                callback.onResponse(this@ResultCall, Response.success(tmdbResponse))
            }
        })
    }

    override fun cloneImpl() = ResultCall(proxy.clone())

    override fun timeout(): Timeout {
        return Timeout.NONE
    }
}
