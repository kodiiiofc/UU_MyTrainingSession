package com.kodiiiofc.urbanuniversity.mytrainingsession

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ExercisesActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var exercisesLV: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        exercisesLV = findViewById(R.id.lv_exercises)
        exercisesLV.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ExerciseDataBase.exercises)

        exercisesLV.setOnItemClickListener { parent, view, position, id ->

            intent = Intent(this, MainActivity::class.java)
            intent.putExtra("exerciseIndex", position)
            startActivity(intent)

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

}