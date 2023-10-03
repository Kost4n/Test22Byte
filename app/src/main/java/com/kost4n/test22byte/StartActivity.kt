package com.kost4n.test22byte

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.kost4n.test22byte.databinding.ActivityStartBinding

class StartActivity: AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        binding.buttonPlay.setOnClickListener {
            startActivity(Intent(this, ChoseDifficultyActivity::class.java))
            this@StartActivity.finish()
        }
    }
}