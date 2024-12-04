package com.example.starwarsblasterstournament.Model

import com.google.gson.annotations.SerializedName

data class Match(
    @SerializedName("match")val matchId: Int,
    @SerializedName("player1")val player1:Players,
    @SerializedName("player2")val player2: Players
)
data class Players(
    @SerializedName("id") val id: String,
    @SerializedName("score")var points: Int = 0,
    val name:String="",
    var totalScore: Int = 0
)
