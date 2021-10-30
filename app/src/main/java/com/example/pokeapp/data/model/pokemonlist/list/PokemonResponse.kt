package com.example.pokeapp.data.model.pokemonlist.list

import androidx.versionedparcelable.VersionedParcelize
import java.io.Serializable

@VersionedParcelize
data class PokemonResponse(
    val results: List<Character>
) : Serializable