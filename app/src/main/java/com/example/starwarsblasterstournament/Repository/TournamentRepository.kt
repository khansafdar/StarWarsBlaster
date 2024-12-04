package com.example.starwarsblasterstournament.Repository

import com.example.starwarsblasterstournament.Model.Match
import com.example.starwarsblasterstournament.Model.Player
import com.example.starwarsblasterstournament.Services.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TournamentRepository {

    private val api by lazy{
        try {
            RetrofitInstance.api
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }


    suspend fun fetchPlayers(): List<Player> = withContext(Dispatchers.IO){
        api.getPlayers()
    }
    suspend fun fetchMatches(): List<Match> =  withContext(Dispatchers.IO) {
        api.getMatches()
    }

    fun calculatePoints(players: List<Player>, matches: List<Match>): List<Player> {
        val playerMap = players.associateBy { it.id }

        matches.forEach { match ->
            val player1 = playerMap[match.player1.id]
            val player2 = playerMap[match.player2.id]

            player1?.totalScore = player1?.totalScore?.plus(match.player1.points) ?: 0
            player2?.totalScore = player2?.totalScore?.plus(match.player2.points) ?: 0

            when {
                match.player1.totalScore > match.player2.totalScore -> {
                    player1?.points = player1?.points?.plus(3) ?: 0
                }
                match.player1.totalScore < match.player2.totalScore -> {
                    player2?.points = player2?.points?.plus(3) ?: 0
                }
                else -> {
                    player1?.points = player1?.points?.plus(1) ?: 0
                    player2?.points = player2?.points?.plus(1) ?: 0
                }
            }
        }

        return players.sortedWith(compareByDescending<Player> { it.points }.thenByDescending { it.totalScore })
    }
}