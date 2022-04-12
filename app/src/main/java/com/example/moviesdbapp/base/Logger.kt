package com.example.moviesdbapp.base

import android.util.Log
import com.example.moviesdbapp.BuildConfig

object Logger {
    private const val DEFAULT_TAG = "Logger"
    private const val isDebug = BuildConfig.IS_LOGS_ENABLED

    fun d(msg: String) {
        if (isDebug) {
            Log.d(DEFAULT_TAG, "$className $msg")
        }
    }

    fun e(msg: String, tr: Throwable) {
        if (isDebug) {
            Log.d(DEFAULT_TAG, "$className $msg", tr)
        }
    }

    fun e(tr: Throwable) {
        if (isDebug) {
            Log.d(DEFAULT_TAG, className, tr)
        }
    }

    fun i(msg: String) {
        if (isDebug) {
            Log.i(DEFAULT_TAG, "$className $msg")
        }
    }

    fun v(msg: String) {
        if (isDebug) {
            Log.v(DEFAULT_TAG, "$className $msg")
        }
    }

    fun w(msg: String) {
        if (isDebug) {
            Log.w(DEFAULT_TAG, "$className $msg")
        }
    }

    fun wtf(msg: String) {
        if (isDebug) {
            Log.wtf(DEFAULT_TAG, "$className $msg")
        }
    }

    private val className: String
        get() {
            val className = Throwable().stackTrace
                .first { it.className != Logger::class.java.name }
                .className
                .substringAfterLast(".")
            return "|$className|"
        }
}