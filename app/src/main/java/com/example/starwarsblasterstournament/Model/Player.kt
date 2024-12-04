package com.example.starwarsblasterstournament.Model

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("id") val id: String,
    @SerializedName("name")val name: String,
    @SerializedName("icon")val avatarUrl: String,
    var points: Int = 0,
    var totalScore: Int = 0
)

