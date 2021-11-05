package com.example.pokeapp.view.stat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.pokeapp.R
import com.example.pokeapp.data.model.detail.PokemonDetailResponse
import com.example.pokeapp.databinding.FragmentStatsBinding
import com.example.pokeapp.view.dashboard.viewmodel.DashboardViewModel

class StatsFragment : Fragment(R.layout.fragment_stats) {

    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!
    private val viewModel : DashboardViewModel by viewModels()
    private lateinit var size : PokemonDetailResponse

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateUi()
    }

    private fun populateUi() {
    }
}