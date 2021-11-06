package com.example.pokeapp.view.stat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentStatsBinding
import com.example.pokeapp.view.dashboard.viewmodel.DashboardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatsFragment : Fragment(R.layout.fragment_stats) {
    private val viewModel: DashboardViewModel by viewModel()
    private var viewBinding: FragmentStatsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentStatsBinding.bind(view)

        val id = checkNotNull(arguments?.getString("id"))
        viewModel.getPokemonById(id).observe(viewLifecycleOwner, Observer { pokemonValue ->
            pokemonValue?.let { pokemon ->
                viewBinding?.apply {

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
                }
            }
        })
    }

    override fun onDestroyView() {
        viewBinding = null
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