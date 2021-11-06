package com.example.pokeapp.view.evolution

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapp.R
import com.example.pokeapp.data.model.home.Pokemon
import com.example.pokeapp.databinding.FragmentEvolutionBinding
import com.example.pokeapp.view.dashboard.viewmodel.DashboardViewModel
import com.example.pokeapp.view.evolution.adapter.EvolutionAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class EvolutionFragment : Fragment(R.layout.fragment_evolution) {

    private var _binding : FragmentEvolutionBinding? = null
    private val binding get() = _binding!!
    private val viewModel : DashboardViewModel by viewModel()

    companion object {
        @JvmStatic
        fun newInstance(id: String?) = EvolutionFragment().apply {
            arguments = Bundle().apply {
                putString("id", id)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEvolutionBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEvolutionBinding.bind(view)

        val id = checkNotNull(arguments?.getString("id"))
        val rv = binding.rvEvolution
        val layoutManager = LinearLayoutManager(requireContext())
        rv.layoutManager = layoutManager
        val adapter = EvolutionAdapter(view.context)
        rv.adapter = adapter
        viewModel.getPokemonById(id).observe(viewLifecycleOwner, { value ->
                value.let { pokemon ->
                    val evolutions = pokemon.evolutions ?: emptyList()
                    viewModel.getEvolutionByIds(evolutions).observe(viewLifecycleOwner, {
                            val pokemon : List<Pokemon> = it
                            adapter.setList(pokemon)
                            adapter.notifyDataSetChanged()

                            if (pokemon.isEmpty()) {
                                binding.tvNonEvolving.visibility = View.VISIBLE
                            }
                        })
                }
            })
    }

}