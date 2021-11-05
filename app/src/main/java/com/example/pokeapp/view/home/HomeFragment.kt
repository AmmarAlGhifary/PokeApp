package com.example.pokeapp.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentHomeBinding
import com.example.pokeapp.view.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var selectedPokemonId: String? = null
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (selectedPokemonId != null) {
            postponeEnterTransition()
        }
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        val layoutManager = LinearLayoutManager(context)
        binding.rvMain.layoutManager = layoutManager

        viewModel.getListPokemon().observe(viewLifecycleOwner, Observer { pokemons ->
            binding.rvMain.adapter = HomeAdapter(
                list = pokemons,
                itemClickedListener = { pokemon, viewHolder ->
                    selectedPokemonId = pokemon.id

                    val extras =
                        FragmentNavigatorExtras(viewHolder.itemView to viewHolder.binding.ivPokemon.transitionName)

                    val bundle = bundleOf("id" to pokemon.id, "name" to pokemon.name)

                    findNavController().navigate(
                        R.id.action_homeFragment_to_detailFragment, bundle, null
                    )
                }, imageLoadedListener = { pokemon, _ ->
                    if (pokemon.id == selectedPokemonId) {
                        startPostponedEnterTransition()
                    }
                })
            if (pokemons.isNotEmpty()) {
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}