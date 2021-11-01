package com.example.pokeapp.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pokeapp.R
import com.example.pokeapp.data.viewmodel.HomeViewModel
import com.example.pokeapp.databinding.FragmentEvolutionBinding
import com.example.pokeapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
@AndroidEntryPoint
class EvolutionFragment : Fragment(R.layout.fragment_evolution) {

    @FragmentScoped
    private var _binding : FragmentEvolutionBinding? = null
    private val binding get() = _binding!!
    private val viewModel : HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEvolutionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        TODO("Not yet implemented")
    }
}