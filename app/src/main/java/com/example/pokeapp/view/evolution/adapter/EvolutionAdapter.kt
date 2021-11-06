package com.example.pokeapp.view.evolution.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.pokeapp.R
import com.example.pokeapp.data.model.home.Pokemon
import com.example.pokeapp.databinding.ItemPokemonBinding

class EvolutionAdapter : RecyclerView.Adapter<EvolutionAdapter.ViewHolder>() {

    private val list = arrayListOf<Pokemon>()

    fun setList(list: List<Pokemon>) {
        this.list.clear()
        this.list.addAll(list)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val poke = list[position]
        holder.bind(poke)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemPokemonBinding.bind(itemView)
        fun bind(poke: Pokemon) {
            with(binding) {
                binding.tvPokemon.text = poke.name
                binding.tvNumber.text = poke.id
                ivPokemon.transitionName = poke.name

                Glide.with(itemView.context)
                    .load(poke.imageurl)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .placeholder(R.drawable.ic_launcher_background)
                    .fallback(ColorDrawable(Color.GRAY))
                    .into(ivPokemon)

                poke.typeofpokemon?.getOrNull(0).let { firstType ->
                    tvType1.text = firstType
                    tvType1.isVisible = firstType != null
                }

                poke.typeofpokemon?.getOrNull(1).let { secondType ->
                    tvType2.text = secondType
                    tvType2.isVisible = secondType != null
                }

                poke.typeofpokemon?.getOrNull(2).let { thirdType ->
                    tvType3.text = thirdType
                    tvType3.isVisible = thirdType != null
                }


            }
        }
    }
}