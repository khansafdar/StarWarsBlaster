package com.example.starwarsblasterstournament.ViewModel

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsblasterstournament.Model.Match
import com.example.starwarsblasterstournament.Model.Player
import com.example.starwarsblasterstournament.Repository.TournamentRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TournamentViewModel : ViewModel() {

    private val repository = TournamentRepository()
    val players = MutableLiveData<List<Player>>()
    val matches = MutableLiveData<List<Match>>()

    fun loadTournamentData(context : Context) {
        viewModelScope.launch {
            try {
                val playersList = repository.fetchPlayers()
                val matchesList = repository.fetchMatches()
                players.value = repository.calculatePoints(playersList, matchesList)
                matches.value = matchesList
                Log.i("safdarkhan", "$playersList")
                Log.i("safdarkhan", "$matchesList")
                Log.i("safdarkhan", "${matches.value}")
            }catch (e: HttpException){
                Toast.makeText(context, "Error code " + e.code(), Toast.LENGTH_LONG).show()
            }


        }
    }
}