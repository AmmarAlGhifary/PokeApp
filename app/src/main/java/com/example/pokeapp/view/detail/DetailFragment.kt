package com.example.pokeapp.view.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.pokeapp.R
import com.example.pokeapp.data.model.home.Pokemon
import com.example.pokeapp.databinding.FragmentDetailBinding
import com.example.pokeapp.view.adapter.PagerSectionAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    @FragmentScoped
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var pokemon: Pokemon

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
        pokemon = args.poke
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
        var url = pokemon.url.substringAfter("pokemon/")
        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + url.substringBefore("/") + ".png"
        binding.apply {
            tvPokemonNameDetail.text = pokemon.name
            Glide.with(this@DetailFragment)
                .load(imageUrl)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_launcher_background)
                .fallback(ColorDrawable(Color.GRAY))
                .into(ivPokemonDetail)
        }
    }

}