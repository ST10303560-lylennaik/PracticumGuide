package com.example.musicplaylistmanagere

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DescriptionScreen : AppCompatActivity() {

    private lateinit var txtSongs: TextView
    private lateinit var txtAvg: TextView
    private lateinit var btnAvg: Button
    private lateinit var btnBack: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_screen)

        txtSongs = findViewById(R.id.txtSongs)
        txtAvg = findViewById(R.id.txtAverageRating)
        btnAvg = findViewById(R.id.btnCalculateAverage)
        btnBack = findViewById(R.id.btnBackToMain)

        displaySongs()

        btnAvg.setOnClickListener {
            calculateAverageRating()
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displaySongs() {
        if (SongData.songs.isEmpty()) {
            txtSongs.text = "No songs in the playlist."
            return
        }

        val builder = StringBuilder()
        for (i in SongData.songs.indices) {
            builder.append("${SongData.songs[i]}\n")
            builder.append("Artist: ${SongData.artists[i]}\n")
            builder.append("Rating: ${SongData.ratings[i]}/5\n")
            builder.append("Comment: ${SongData.comments[i]}\n\n")
        }
        txtSongs.text = builder.toString()
    }

    @SuppressLint("SetTextI18n")
    private fun calculateAverageRating() {
        if (SongData.ratings.isEmpty()) {
            txtAvg.text = "No ratings yet."
            return
        }
        val average = SongData.ratings.average()
        txtAvg.text = " Average Rating: %.2f".format(average)
    }
}

