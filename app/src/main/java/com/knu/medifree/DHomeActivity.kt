package com.knu.medifree

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent as Intent

class DHomeActivity : AppCompatActivity() {
    lateinit var btn_app: ImageButton
    lateinit var btn_quest: ImageButton
    lateinit var btn_office: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_home)

        btn_app = findViewById<ImageButton>(R.id.d_appoint)
        btn_quest = findViewById<ImageButton>(R.id.d_quest)
        btn_office = findViewById<ImageButton>(R.id.d_office)

        btn_app.setOnClickListener() {
            val intent = Intent(applicationContext, DAppActivity::class.java)
            startActivity(intent)
        }

        btn_quest.setOnClickListener() {
            val intent = Intent(applicationContext, DQuestActivity::class.java)
            startActivity(intent)
        }

        btn_office.setOnClickListener() {
            val intent = Intent(applicationContext, DOfficeActivity::class.java)
            startActivity(intent)
        }

    }
}