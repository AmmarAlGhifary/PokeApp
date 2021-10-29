package com.example.pokeapp.data.model.pokemondetail


import androidx.versionedparcelable.VersionedParcelize
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@VersionedParcelize
data class CharacterResponse(
    val abilities: List<Ability>,
    val baseExperience: Int,
    val forms: List<Form>,
    val gameIndices: List<GameIndice>,
    val height: Int,
    val heldItems: List<Any>,
    val id: Int,
    val isDefault: Boolean,
    val locationAreaEncounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val pastTypes: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int

) : Serializable