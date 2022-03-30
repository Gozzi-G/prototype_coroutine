package com.example.moviesdbapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesdbapp.databinding.ActivityMainBinding
import com.example.moviesdbapp.ui.movies.movies.MovieViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by inject()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        viewModel.onViewCreated()

    }

}