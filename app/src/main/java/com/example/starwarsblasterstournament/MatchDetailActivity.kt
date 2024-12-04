package com.example.starwarsblasterstournament

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsblasterstournament.Model.Match
import com.example.starwarsblasterstournament.ViewModel.TournamentViewModel

class MatchDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: TournamentViewModel
    private lateinit var playerMatches:List<Match>
    private lateinit var matchAdapter: MatchAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_details)

        val playerId = intent.getStringExtra("PLAYER_ID")

        viewModel = ViewModelProvider(this)[TournamentViewModel::class.java]

        recyclerView = findViewById(R.id.recyclerView_matches)
        recyclerView.layoutManager = LinearLayoutManager(this)

        matchAdapter = MatchAdapter(emptyList(), playerId ?: "")
        recyclerView.adapter = matchAdapter
//        viewModel.matches.value?.let { matchAdapter.updateMatches(it) }

        // Filter and observe matches for the specific player

        playerMatches.filter {
            (it.player1.id == playerId) || (it.player2.id == playerId)
        }
        Log.i("safdarkhan","${viewModel.matches.value.toString()}")
        Log.i("safdarkhan","${playerMatches.toString()} $playerId")
        matchAdapter.updateMatches(playerMatches)

    }
}