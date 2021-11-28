package com.knu.medifree

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class TypeActivity : AppCompatActivity() {
    lateinit var btn_patient: ImageButton
    lateinit var btn_doctor: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type)

        // 객체 할당
        btn_patient = findViewById<ImageButton>(R.id.type_btn_patient)
        btn_doctor = findViewById<ImageButton>(R.id.type_btn_doctor)

        // 클릭 리스너 할당
        btn_patient.setOnClickListener {
            val intent = Intent(applicationContext, SignupPatientActivity::class.java)
            startActivity(intent)
            finish()
        }
        btn_doctor.setOnClickListener {
            val intent = Intent(applicationContext, SignupDoctorActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}