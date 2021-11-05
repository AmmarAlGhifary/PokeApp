package com.example.pokeapp.view.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
import com.example.pokeapp.data.model.home.Pokemon
import com.example.pokeapp.databinding.ItemPokemonBinding
import com.example.pokeapp.view.home.HomeFragmentDirections


class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }

    }

    var onItemClick : ((Pokemon) -> Unit)? = null

    val differ = AsyncListDiffer(this, callback)
    var dataPoke : List<Pokemon>
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

    inner class ViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPokemonBinding.bind(itemView)
        fun bind(poke : Pokemon) {
            with(binding) {
                var url = poke.url.substringAfter("pokemon/")
                val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+url.substringBefore("/")+".png"
                Glide.with(itemView.context)
                    .load(imageUrl)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .placeholder(R.drawable.ic_launcher_background)
                    .fallback(ColorDrawable(Color.GRAY))
                    .into(ivPokemon)
                tvPokemon.text = poke.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(dataPoke[adapterPosition])
            }
        }
    }

}