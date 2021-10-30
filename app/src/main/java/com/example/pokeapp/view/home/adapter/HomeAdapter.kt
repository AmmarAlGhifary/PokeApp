package com.example.pokeapp.view.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.pokeapp.R
import com.example.pokeapp.data.model.pokemonlist.list.Character
import com.example.pokeapp.databinding.ItemPokemonBinding
import com.example.pokeapp.view.home.HomeFragmentDirections


class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)
    var dataPoke : List<Character>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val poke = dataPoke[position]

        holder.bind(dataPoke[position])
        holder.itemView.setOnClickListener { mViews ->
            val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(poke)
            mViews.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int = dataPoke.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPokemonBinding.bind(itemView)
        fun bind(poke : Character) {
            with(binding) {
                var url = poke.url.substringAfter("pokemon/")
                val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+url.substringBefore("/")+".png"
                Glide.with(itemView.context)
                    .load(imageUrl)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_error)
                    .into(ivPokemon)
                tvPokemonName.text = poke.name
                tvNumber.text = poke.name


            }
        }
    }

}