package com.example.pokeapp.view.stat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.R
import com.example.pokeapp.data.model.home.Pokemon
import com.example.pokeapp.databinding.ItemWeaknessBinding

class StatsAdapter : RecyclerView.Adapter<StatsAdapter.ViewHolder>() {

    private val list = arrayListOf<Pokemon>()

    fun setList(list: List<Pokemon>) {
        this.list.clear()
        this.list.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_weakness, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val poke = list[position]
        holder.bind(poke)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemWeaknessBinding.bind(itemView)
        fun bind(poke: Pokemon) {
            binding.tvWeakness.text = poke.weaknesses.toString()
        }
    }
}