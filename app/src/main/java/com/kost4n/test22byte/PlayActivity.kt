package com.kost4n.test22byte

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.util.Log
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.kost4n.test22byte.databinding.ActivityPlayBinding

class PlayActivity: AppCompatActivity() {
    private lateinit var binding: ActivityPlayBinding

    private lateinit var timer: CountDownTimer
    private lateinit var difficulty: String
    private var correctAnswer = ""
    private var countAnswers = 0
    private var countCorrectAnswers = 0
    private val arrayQuestions = mapOf(
        R.drawable.watch1 to "абакан",
        R.drawable.watch2 to "москва",
        R.drawable.watch3 to "питер",
        R.drawable.watch4 to "казань",
        R.drawable.watch5 to "сорск",
        R.drawable.watch6 to "анапа"
    )
    private lateinit var array: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        difficulty = intent?.extras?.getString("diff")!!

        array = arrayQuestions.keys.shuffled().toTypedArray()
        startGame()
        binding.buttonCheck.setOnClickListener {
            checkAnswer(binding.edittextAnswer.text.toString().toLowerCase())
            binding.edittextAnswer.text = Editable.Factory.getInstance().newEditable("")
        }
    }

    private fun checkAnswer(answer: String) {
        if (answer == correctAnswer)
            countCorrectAnswers++
        countAnswers++
        if (difficulty == "time") {
            timer.cancel()
        }
        startGame()
    }

    private fun startGame() {
        if (difficulty == "time") {
            binding.timer.visibility = View.VISIBLE
            startTimer()
        }
        if (countAnswers < arrayQuestions.size) {
            binding.image.setImageResource(array[countAnswers])
            correctAnswer = arrayQuestions[array[countAnswers]].toString()
            Log.d("PA", "-------------------$correctAnswer----------------------")
        } else {
            startActivity(
                Intent(this, ResultActivity::class.java)
                    .putExtra("countCorrectAnswers", countCorrectAnswers)
            )
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(10000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.timer.text = "0: " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                binding.edittextAnswer.text = Editable.Factory.getInstance().newEditable("")
                countAnswers++
                startGame()
            }
        }.start()
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }
}