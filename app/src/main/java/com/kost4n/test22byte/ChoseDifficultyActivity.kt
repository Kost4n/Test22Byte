package com.kost4n.test22byte

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.kost4n.test22byte.databinding.ActivityChoseDifficultyBinding

class ChoseDifficultyActivity: AppCompatActivity() {
    private lateinit var binding: ActivityChoseDifficultyBinding
    private lateinit var intent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoseDifficultyBinding.inflate(layoutInflater)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        intent = Intent(this, MemorizeActivity::class.java)
        binding.buttonCasual.setOnClickListener {
            startActivity(intent.putExtra("diff", "casual"))
        }
        binding.buttonTime.setOnClickListener {
            startActivity(intent.putExtra("diff", "time"))
        }
    }
}