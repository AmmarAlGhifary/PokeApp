package com.example.pokeapp.data.model.detail


import com.google.gson.annotations.SerializedName

data class Move(
    @SerializedName("move")
    val move: MoveX,
    @SerializedName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>
)