package com.example.pokeapp.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapp.R
import com.example.pokeapp.data.viewmodel.HomeViewModel
import com.example.pokeapp.databinding.FragmentHomeBinding
import com.example.pokeapp.view.home.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    @FragmentScoped
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeAdapter: HomeAdapter

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRV()
    }


    private fun getRV() {
        homeAdapter = HomeAdapter()
        binding.rvMain.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }
        viewModel.pokemonResponse.observe(requireActivity(), { result ->
            homeAdapter.dataPoke = result.results
        })
    }

}