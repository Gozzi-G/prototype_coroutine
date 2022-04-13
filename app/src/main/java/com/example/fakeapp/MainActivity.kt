package com.example.fakeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fakeapp.base.states.StateLCE
import com.example.fakeapp.base.states.ViewState
import com.example.fakeapp.databinding.ActivityMainBinding
import com.example.fakeapp.ui.movies.movies.MovieViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by inject()

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewState: ViewState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        initStateLCE()
        viewModel.onViewCreated()
    }

    private fun initStateLCE() {
        viewModel.observeStateLCE(this) {state->
            when (state) {
                is StateLCE.Loading -> viewState.load()
                is StateLCE.Content -> viewState.content()
                is StateLCE.Error -> viewState.error(state.message)
            }
        }
    }
}