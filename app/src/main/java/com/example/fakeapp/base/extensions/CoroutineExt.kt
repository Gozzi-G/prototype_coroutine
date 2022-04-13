package com.example.fakeapp.base.extensions

import kotlinx.coroutines.*

fun CoroutineScope.launchOnIO(
    block: suspend CoroutineScope.() -> Unit
): Job {
    return launch(Dispatchers.IO, CoroutineStart.DEFAULT, block)
}

fun CoroutineScope.launchOnMain(
    block: suspend CoroutineScope.() -> Unit
): Job {
    return launch(Dispatchers.Main, CoroutineStart.DEFAULT, block)
}