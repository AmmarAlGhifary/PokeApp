package com.example.pokeapp.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.pokeapp.R
import com.example.pokeapp.data.viewmodel.HomeViewModel
import com.example.pokeapp.databinding.ActivityDetailBinding
import com.example.pokeapp.view.detail.adapter.PagerSectionAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {ActivityDetailBinding.inflate(layoutInflater)}
    private val viewModel : HomeViewModel by viewModels()

    companion object {
        const val INFORMATION_POKEMON = "information_pokemon"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_evolution,
            R.string.tab_stats
        )

    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        val sectionsPagerAdapter = PagerSectionAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f

    }
}