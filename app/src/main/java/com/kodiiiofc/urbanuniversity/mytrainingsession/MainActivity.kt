package com.kodiiiofc.urbanuniversity.mytrainingsession

import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    private val exercises = ExerciseDataBase.exercises
    private var exerciseIndex = 0
    private lateinit var timer: CountDownTimer
    private lateinit var currentExercise: Exercise

    private lateinit var titleTV: TextView
    private lateinit var exerciseTV: TextView
    private lateinit var descriptionTV: TextView
    private lateinit var timerTV: TextView

    private lateinit var startBTN: Button
    private lateinit var complitedBTN: Button

    private lateinit var imageIV: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        exerciseTV = findViewById(R.id.tv_exercise)
        descriptionTV = findViewById(R.id.tv_description)
        timerTV = findViewById(R.id.tv_timer)

        startBTN = findViewById(R.id.btn_start)
        complitedBTN = findViewById(R.id.btn_complited)

        imageIV = findViewById(R.id.iv_image)

        exerciseIndex = intent.getIntExtra("exerciseIndex", 0)
        currentExercise = exercises[exerciseIndex]
        exerciseTV.text = currentExercise.name
        descriptionTV.text = currentExercise.description
        imageIV.setImageResource(currentExercise.gifImage)
        timerTV.text = formatTime(currentExercise.durationInSeconds)

        startBTN.setOnClickListener {
            startWorkout()
        }
        complitedBTN.setOnClickListener {
            complitedExercise()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_exit -> finishAffinity()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun complitedExercise() {
        finish()
    }

    private fun startWorkout() {

        startBTN.isEnabled = false
        startBTN.text = "Процесс тренировки"
        startNextExercise()
    }

    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)

    }

    private fun startNextExercise() {
        if (exerciseIndex < exercises.size) {
            timer =
                object : CountDownTimer((currentExercise.durationInSeconds * 1000).toLong(), 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        timerTV.text = formatTime((millisUntilFinished / 1000).toInt())
                    }

                    override fun onFinish() {
                        timerTV.text = "Упражнение завершено"
                        imageIV.visibility = View.VISIBLE
                        complitedBTN.isEnabled = true
                        imageIV.setImageResource(0)
                    }
                }.start()
        } else {
            exerciseTV.text = "Тренировка завершена"
            descriptionTV.text = ""
            titleTV.text = ""
            complitedBTN.isEnabled = true
            startBTN.isEnabled = false
        }
    }
}