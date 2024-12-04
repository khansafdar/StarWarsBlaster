package com.example.starwarsblasterstournament

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsblasterstournament.Model.Player

class PointsTableAdapter(
    private var players: List<Player>,
    private val onPlayerClick: (Player) -> Unit
) : RecyclerView.Adapter<PointsTableAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val playerName = itemView.findViewById<TextView>(R.id.playerName)
        private val playerPoints = itemView.findViewById<TextView>(R.id.playerPoints)

        fun bind(player: Player) {
            playerName.text = player.name
            playerPoints.text = player.points.toString()
            itemView.setOnClickListener { onPlayerClick(player) }
        }
    }
    fun updatePlayers(newPlayers: List<Player>) {
        players = newPlayers
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(players[position])
    }

    override fun getItemCount() = players.size
}