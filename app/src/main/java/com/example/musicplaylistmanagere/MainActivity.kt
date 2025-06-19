package com.example.musicplaylistmanagere

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd = findViewById<Button>(R.id.btnAddToPlaylist)
        val btnView = findViewById<Button>(R.id.btnViewDetails)
        val btnExit = findViewById<Button>(R.id.btnExit)

        btnAdd.setOnClickListener {
            showAddDialog()
        }

        btnView.setOnClickListener {
            val intent = Intent(this, DescriptionScreen::class.java)
            startActivity(intent)
        }

        btnExit.setOnClickListener {
            finishAffinity() // Closes the app
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun showAddDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_song, null)

        val songInput = dialogView.findViewById<EditText>(R.id.editSongTitle)
        val artistInput = dialogView.findViewById<EditText>(R.id.editArtist)
        val ratingInput = dialogView.findViewById<EditText>(R.id.editRating)
        val commentInput = dialogView.findViewById<EditText>(R.id.editComment)

        AlertDialog.Builder(this)
            .setTitle("Add to Playlist")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val song = songInput.text.toString()
                val artist = artistInput.text.toString()
                val ratingStr = ratingInput.text.toString()
                val comment = commentInput.text.toString()

                // If the song is empty, show an error
                if (song.isEmpty()) {
                    Toast.makeText(this, "Please enter a song title", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                // Validate rating
                val rating = ratingStr.toIntOrNull()
                if (rating == null || rating !in 1..5) {
                    Toast.makeText(this, "Rating must be between 1 and 5", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                // Add to arrays
                SongData.songs.add(song)
                SongData.artists.add(artist)
                SongData.ratings.add(rating)
                SongData.comments.add(comment)

                Toast.makeText(this, "Song added!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
