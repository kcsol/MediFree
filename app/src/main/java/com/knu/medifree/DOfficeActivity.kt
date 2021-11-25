package com.knu.medifree

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DOfficeActivity : AppCompatActivity() {
    private lateinit var office_btn : Button
    private lateinit var list_reservations : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_office)


        office_btn = findViewById<Button>(R.id.d_office_btn)
        //진료실 버튼
        office_btn.setOnClickListener {
        //webrtc 진료 시작
        }


    }
}