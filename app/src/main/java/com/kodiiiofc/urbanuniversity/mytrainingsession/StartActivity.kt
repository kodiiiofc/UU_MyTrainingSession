package com.kodiiiofc.urbanuniversity.mytrainingsession

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class StartActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    private lateinit var trainingBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        trainingBTN = findViewById(R.id.btn_training)

        trainingBTN.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
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