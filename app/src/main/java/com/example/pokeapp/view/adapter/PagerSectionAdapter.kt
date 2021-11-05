package com.example.pokeapp.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pokeapp.view.evolution.EvolutionFragment
import com.example.pokeapp.view.stat.StatsFragment

class PagerSectionAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = EvolutionFragment()
            1 -> fragment = StatsFragment()
        }
        return fragment as Fragment
    }

}