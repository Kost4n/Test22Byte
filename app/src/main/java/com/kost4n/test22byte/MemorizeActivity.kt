package com.kost4n.test22byte

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.kost4n.test22byte.databinding.ActivityMemorizeBinding

class MemorizeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMemorizeBinding
    private lateinit var playIntent: Intent
    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemorizeBinding.inflate(layoutInflater)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        val difficulty = intent.extras?.getString("diff")
        playIntent = Intent(this, PlayActivity::class.java).putExtra("diff", difficulty)
        startTimer()

        binding.buttonPlay.setOnClickListener {
            timer.cancel()
            startActivity(playIntent)
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(10000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.timer.text = "0: " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                startActivity(playIntent)
            }
        }.start()
    }
}