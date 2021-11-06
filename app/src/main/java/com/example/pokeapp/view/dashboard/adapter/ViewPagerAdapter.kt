package com.example.pokeapp.view.dashboard.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pokeapp.R
import com.example.pokeapp.view.evolution.EvolutionFragment
import com.example.pokeapp.view.stat.StatsFragment


class ViewPagerAdapter(
    supportFragmentManager: FragmentManager,
    context: Context,
    private val pokemonId: String
) : FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    data class Page(val title: String, val ctor: () -> Fragment)

    private val pages = listOf(
        Page(
            context.getString(R.string.tab_evolution)
        ) { EvolutionFragment.newInstance(pokemonId) },
        Page(
            context.getString(R.string.tab_stats)
        ) { StatsFragment.newInstance(pokemonId) }
    )

    override fun getItem(position: Int): Fragment {
        return pages[position].ctor()
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pages[position].title
    }
}
