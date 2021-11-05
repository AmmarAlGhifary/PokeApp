package com.example.pokeapp.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.pokeapp.R
import com.example.pokeapp.data.model.detail.PokemonDetailResponse
import com.example.pokeapp.data.model.detail.Stat
import com.example.pokeapp.data.viewmodel.HomeViewModel
import com.example.pokeapp.databinding.FragmentStatsBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
@AndroidEntryPoint
class StatsFragment : Fragment(R.layout.fragment_stats) {

    @FragmentScoped
    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!
    private val args: StatsFragmentArgs by navArgs()
    private val sizeArgs : StatsFragmentArgs by navArgs()

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
        size = sizeArgs.size

        populateUi()
    }

    private fun populateUi() {
        binding.apply {
            tvHeight.text = size.height.toString()
            tvWeight.text = size.weight.toString()

        }
    }
}