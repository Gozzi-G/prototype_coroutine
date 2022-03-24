package com.example.moviesdbapp.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.moviesdbapp.base.states.StateLCE
import org.koin.core.component.KoinComponent

abstract class BaseViewModel:ViewModel(), KoinComponent {

    private val stateLCE = MutableLiveData<StateLCE>()

    fun observeStateLCE(owner: LifecycleOwner, observer: Observer<StateLCE>) {
        stateLCE.observe(owner, observer)
    }

}