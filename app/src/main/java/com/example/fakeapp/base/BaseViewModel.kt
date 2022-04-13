package com.example.fakeapp.base

import androidx.lifecycle.*
import com.example.fakeapp.App
import com.example.fakeapp.base.states.StateLCE
import org.koin.core.component.KoinComponent

abstract class BaseViewModel : AndroidViewModel(App.instance), KoinComponent {

    private val stateLCE = MutableLiveData<StateLCE>()

    fun observeStateLCE(owner: LifecycleOwner, observer: Observer<StateLCE>) {
        stateLCE.observe(owner, observer)
    }

    protected fun loading() {
        stateLCE.value = StateLCE.Loading()
    }

    protected fun content() {
        stateLCE.value = StateLCE.Content()
    }

    protected fun error(
        throwable: Throwable? = null,
        message: String = ""
    ) {
        stateLCE.value = StateLCE.Error(throwable, message)
    }

    fun getString(resId: Int, vararg args : Any): String {
        return getApplication<App>().getString(resId, *args)
    }

}