package com.example.pokeapp.data.model.detail


data class PokemonDetailResponse(
    val abilities: List<Ability>,
    val height: Int,
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
) {
    data class Ability(
        val ability: Ability,
    ) {
        data class Ability(
            val name: String,
            val url: String
        )
    }

    data class Sprites(
        val frontDefault: String?,
        val other: Other
    ) {
        data class Other(
            val officialArtwork: OfficialArtwork
        ) {
            data class OfficialArtwork(
                val frontDefault: String?
            )
        }
    }

    data class Stat(
        val baseStat: Int,
        val effort: Int,
        val stat: Stat
    ) {
        data class Stat(
            val name: String
        )
    }

    data class Type(
        val type: Type
    ) {
        data class Type(
            val name: String
        )
    }
}