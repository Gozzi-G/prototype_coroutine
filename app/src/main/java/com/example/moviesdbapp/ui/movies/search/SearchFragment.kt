package com.example.moviesdbapp.ui.movies.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviesdbapp.databinding.FragmentSearchLayoutBinding
import java.lang.RuntimeException

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchLayoutBinding? = null
    private val binding: FragmentSearchLayoutBinding
        get() = _binding ?: throw RuntimeException("FragmentSearchLayoutBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}