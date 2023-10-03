package com.kost4n.test22byte

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.kost4n.test22byte.databinding.ActivityResultBinding

class ResultActivity: AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        val correctAnswers = intent.extras?.getInt("countCorrectAnswers", 0)
        val text =  binding.result.text.toString().replace("_", correctAnswers.toString())
        binding.result.text = text

        binding.buttonRestart.setOnClickListener {
            startActivity(Intent(this, ChoseDifficultyActivity::class.java))
            this@ResultActivity.finish()
        }
    }
}