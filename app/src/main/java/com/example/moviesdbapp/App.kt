package com.example.moviesdbapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.moviesdbapp.di.appModule
import com.example.moviesdbapp.di.repositoriesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            module {
                appModule
                repositoriesModule
            }
        }

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}