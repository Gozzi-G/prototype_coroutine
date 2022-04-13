package com.example.fakeapp.ui.movies.movies

import androidx.lifecycle.viewModelScope
import com.example.fakeapp.base.BaseViewModel
import com.example.fakeapp.base.MmDbLogger
import com.example.fakeapp.base.extensions.launchOnIO
import com.example.fakeapp.data.repositories.fake_user.FakeRepository

class MovieViewModel(
    private val fakeRepository: FakeRepository
) : BaseViewModel() {

    fun onViewCreated() {
        getFakeUsers()
    }

    private fun getFakeUsers() {
        viewModelScope.launchOnIO {
            fakeRepository.getFakeUsers()
                .onSuccess {
                    MmDbLogger.d("onSuccess: ${it}")
                }
                .onError {
                    MmDbLogger.d("onError: ${it.statusMessage}")
                }
        }
    }
}

