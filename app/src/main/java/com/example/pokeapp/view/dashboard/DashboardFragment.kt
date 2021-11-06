package com.example.pokeapp.view.dashboard

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentDashboardBinding
import com.example.pokeapp.utils.ImageLoadingListener
import com.example.pokeapp.view.dashboard.adapter.ViewPagerAdapter
import com.example.pokeapp.view.dashboard.viewmodel.DashboardViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DashboardViewModel by viewModel()

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_evolution,
            R.string.tab_stats,
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        postponeEnterTransition()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = checkNotNull(arguments?.getString("id"))
        val name = checkNotNull(arguments?.getString("name"))

        _binding = FragmentDashboardBinding.bind(view)
        binding.toolbarLayout.isTitleEnabled
        binding.toolbarHome.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.ivPokemonDetail.transitionName = name

        viewModel.getPokemonById(id).observe(viewLifecycleOwner, { pokemonValue ->
            pokemonValue?.let { pokemon ->
                binding.tvPokemonNameDetail.text = pokemon.name
                binding.tvAboutPokemon.text = pokemon.xdescription


                pokemon.typeofpokemon?.getOrNull(0).let { firstType ->
                    binding.tvTypeDetail1.text = firstType
                    binding.tvTypeDetail1.isVisible = firstType != null
                }

                pokemon.typeofpokemon?.getOrNull(1).let { secondType ->
                    binding.tvTypeDetail2.text = secondType
                    binding.tvTypeDetail2.isVisible = secondType != null
                }

                binding.ivPokemonDetail.let {
                    Glide.with(view.context)
                        .load(pokemon.imageurl)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.ic_error)
                        .placeholder(R.drawable.ic_launcher_background)
                        .fallback(ColorDrawable(Color.GRAY))
                        .listener(ImageLoadingListener {
                            startPostponedEnterTransition()
                        })
                        .into(it)
                }

                val pager = binding.viewPager
                val tabs = binding.tabLayout
                pager.adapter =
                    ViewPagerAdapter(childFragmentManager, requireContext(), pokemon.id)
                tabs.setupWithViewPager(pager)
            }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}


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


//private fun initPager() {
//    val pagerAdapter = activity?.let { PagerSectionAdapter(it) }
//    binding.holderPager.vpDetail.adapter = pagerAdapter
//    TabLayoutMediator(
//        binding.holderPager.tabLayout,
//        binding.holderPager.vpDetail
//    ) { tab, position ->
//        tab.text = resources.getString(DashboardFragment.TAB_TITLES[position])
//    }.attach()
//}