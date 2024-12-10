package com.example.thecontest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView
import android.media.MediaPlayer

class MainActivity : AppCompatActivity() {

    private var score = 0
    private lateinit var scoreValue: TextView
    private lateinit var scoreText: TextView
    private lateinit var clickSound: MediaPlayer
    private lateinit var winSound: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        scoreText = findViewById(R.id.score_text)
        scoreValue = findViewById(R.id.score_value)
        val resetButton: Button = findViewById(R.id.reset_button)
        val addButton: Button = findViewById(R.id.add_button)
        val minusButton: Button = findViewById(R.id.minus_button)
        clickSound = MediaPlayer.create(this, R.raw.beep_short)
        winSound = MediaPlayer.create(this, R.raw.spring_attic_door)

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("score")
        }

        updateScore()

        resetButton.setOnClickListener {
            score = 0
            updateScore()
            playSound(true)
        }
        addButton.setOnClickListener {
            score++
            updateScore()
            playSound(true)
        }
        minusButton.setOnClickListener {
            score--
            updateScore()
            playSound(true)
        }
    }
    private fun updateScore() {
        scoreValue.text = "$score"
        if (score>=15) {
            playSound(false)
        }
    }
    private fun playSound(isClick: Boolean) {
        if (isClick) {
            clickSound.start()
        } else {
            winSound.start()
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("score", score)
        super.onSaveInstanceState(outState)
    }
}