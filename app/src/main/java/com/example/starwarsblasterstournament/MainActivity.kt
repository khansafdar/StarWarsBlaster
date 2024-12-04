package com.example.starwarsblasterstournament

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsblasterstournament.Model.Player
import com.example.starwarsblasterstournament.ViewModel.TournamentViewModel
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TournamentViewModel
    private lateinit var pointsTableAdapter: PointsTableAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this)[TournamentViewModel::class.java]

        // Initialize RecyclerView and set its layout manager
        recyclerView = findViewById(R.id.recyclerView_points)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter with a click listener for each player item
        pointsTableAdapter = PointsTableAdapter(emptyList()) { player ->
            openMatchDetails(player)
        }
        recyclerView.adapter = pointsTableAdapter

        // Observe changes to the players data in ViewModel
        viewModel.players.observe(this, Observer { players ->
            // Update the RecyclerView adapter with new data
            pointsTableAdapter.updatePlayers(players)
        })

        // Load the tournament data

        viewModel.loadTournamentData(this@MainActivity)



    }

    // Function to open the MatchDetailsActivity/Fragment when a player is clicked
    private fun openMatchDetails(player: Player) {
        val intent = Intent(this, MatchDetailsActivity::class.java).apply {
            putExtra("PLAYER_ID", player.id)
        }
        startActivity(intent)
    }
}