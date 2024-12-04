package com.example.starwarsblasterstournament

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsblasterstournament.Model.Match

class MatchAdapter(
    private var matches: List<Match>,
    private val playerId: String
) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val player1Score = itemView.findViewById<TextView>(R.id.player1Name)
        private val player2Score = itemView.findViewById<TextView>(R.id.player2Name)

        fun bind(match: Match) {
            player1Score.text = match.player1.name.toString()
            player2Score.text = match.player2.name.toString()
            // Apply color based on win/loss/draw
        }
    }
    fun updateMatches(newMatches: List<Match>) {
        matches = newMatches
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(matches[position])
    }

    override fun getItemCount() = matches.size
}