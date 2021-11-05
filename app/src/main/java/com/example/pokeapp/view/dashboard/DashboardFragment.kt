package com.example.pokeapp.view.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.pokeapp.R
import com.example.pokeapp.data.model.home.Pokemon
import com.example.pokeapp.view.home.HomeViewModel
import com.example.pokeapp.databinding.FragmentDetailBinding
import com.example.pokeapp.view.adapter.PagerSectionAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

class DashboardFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var pokemon: Pokemon
    private val viewModel: HomeViewModel by viewModels()

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_evolution,
            R.string.tab_stats,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarLayout.isTitleEnabled
        binding.toolbarHome.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        initPager()
        populateUI()
    }

    private fun initPager() {
        val pagerAdapter = activity?.let { PagerSectionAdapter(it) }
        binding.holderPager.vpDetail.adapter = pagerAdapter
        TabLayoutMediator(
            binding.holderPager.tabLayout,
            binding.holderPager.vpDetail
        ) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun populateUI() {
//        var url = pokemon.url.substringAfter("pokemon/")
//        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + url.substringBefore("/") + ".png"
//        binding.apply {
//            tvPokemonNameDetail.text = pokemon.name
//            Glide.with(this@DashboardFragment)
//                .load(imageUrl)
//                .centerCrop()
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .error(R.drawable.ic_error)
//                .placeholder(R.drawable.ic_launcher_background)
//                .fallback(ColorDrawable(Color.GRAY))
//                .into(ivPokemonDetail)
//        }
    }

}