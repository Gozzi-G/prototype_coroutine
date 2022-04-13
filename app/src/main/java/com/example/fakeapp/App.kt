package com.example.fakeapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.fakeapp.di.appModule
import com.example.fakeapp.di.repositoriesModule
import com.example.fakeapp.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var instance: App
        private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                repositoriesModule,
                viewModelsModule
            )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}