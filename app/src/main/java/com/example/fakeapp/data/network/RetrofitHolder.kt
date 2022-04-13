package com.example.fakeapp.data.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.fakeapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitHolder(
    appContext: Context
) {

    companion object {
        private const val BASE_URL = BuildConfig.BASE_URL
    }

    private var retrofit: Retrofit

    init {
        val okHttpClient = OkHttpClient.Builder()

        if (BuildConfig.IS_LOGS_ENABLED) {
            val loginInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            okHttpClient.addInterceptor(loginInterceptor)

            val chuckerInterceptor = ChuckerInterceptor
                .Builder(appContext)
                .build()

            okHttpClient.addInterceptor(chuckerInterceptor)
        }

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient.build())
            .addCallAdapterFactory(TmdbCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    protected fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

}