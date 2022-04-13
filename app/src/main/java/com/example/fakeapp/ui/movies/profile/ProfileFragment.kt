package com.example.fakeapp.ui.movies.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fakeapp.databinding.FragmentProfileLayoutBinding
import java.lang.RuntimeException

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileLayoutBinding? = null
    private val binding: FragmentProfileLayoutBinding
        get() = _binding ?: throw RuntimeException("FragmentProfileLayoutBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}