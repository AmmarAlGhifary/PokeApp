package com.example.pokeapp.view.stat

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokeapp.R
import com.example.pokeapp.data.model.home.Pokemon
import com.example.pokeapp.databinding.FragmentStatsBinding
import com.example.pokeapp.view.dashboard.viewmodel.DashboardViewModel
import com.example.pokeapp.view.stat.adapter.StatsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatsFragment : Fragment(R.layout.fragment_stats) {

    private val viewModel: DashboardViewModel by viewModel()
    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentStatsBinding.bind(view)

        val id = checkNotNull(arguments?.getString("id"))
        val rv = binding.rvWeakness
        val layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = StatsAdapter()
        rv.adapter = adapter
        rv.layoutManager = layoutManager

        viewModel.getPokemonById(id).observe(viewLifecycleOwner, { value ->
            value?.let { pokemon ->
                binding.apply {

                    tvHeight.text = pokemon.height
                    tvWeight.text = pokemon.weight

                    tvHp.text = pokemon.hp.toString()
                    tvAttack.text = pokemon.attack.toString()
                    tvDefense.text = pokemon.defense.toString()
                    tvSpAtk.text = pokemon.special_attack.toString()
                    tvSpDef.text = pokemon.special_defense.toString()
                    tvSpeed.text = pokemon.speed.toString()

                    progressHp.progress = pokemon.hp ?: 0
                    progressAttack.progress = pokemon.attack ?: 0
                    progressDefense.progress = pokemon.defense ?: 0
                    progressSpatk.progress = pokemon.special_attack ?: 0
                    progressSpdef.progress = pokemon.special_defense ?: 0
                    progressSpeed.progress = pokemon.speed ?: 0

                    val weakness = pokemon.weaknesses ?: emptyList()
                    viewModel.getWeaknessByIds(weakness).observe(viewLifecycleOwner, {
                        val pokemon: List<Pokemon> = it
                        adapter.setList(pokemon)
                        adapter.notifyDataSetChanged()
                    })
                }
            }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String?) = StatsFragment().apply {
            arguments = Bundle().apply {
                putString("id", id)
            }
        }
    }
}