package com.example.pokeapp.data.model.home

import androidx.versionedparcelable.VersionedParcelize
import java.io.Serializable

@VersionedParcelize
data class PokemonResponse(
    val results: List<Pokemon>
) : Serializable