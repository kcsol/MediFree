package com.knu.medifree

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.knu.medifree.functions.Account
import com.knu.medifree.functions.Doctor.Companion.doctor
import java.lang.Exception
import java.util.ArrayList
import java.util.HashMap

class SignupDoctor2Activity: AppCompatActivity() {
    private lateinit var btn_reg: ImageButton
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var hospital_name: String
    private lateinit var major: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_doctor_next)

        // Hospital Name Spinner
        val hospitalNameSpinner = findViewById<Spinner>(R.id.hospital_Name)
        arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.spinner_hospital) as Array<String>
        )
        arrayAdapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        hospitalNameSpinner.adapter = arrayAdapter
        hospitalNameSpinner.setSelection(0)

        // Major Name Spinner
        val majorSpinner = findViewById<Spinner>(R.id.major)
        arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.spinner_major) as Array<String>
        )
        arrayAdapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        majorSpinner.adapter = arrayAdapter
        majorSpinner.setSelection(0)


        // 병원_메이저 보내주는 버튼 객체 할당
        btn_reg = findViewById<ImageButton>(R.id.d_reg_btn_reg)

        btn_reg.setOnClickListener {
            hospital_name = hospitalNameSpinner.selectedItem.toString()
            major = majorSpinner.selectedItem.toString()

            //병원정보 저장 후 로그인화면으로 이동
            val intent = getIntent()
            var dname = intent.getStringExtra("name")
            Account.signUpDoctor(dname.toString(), hospital_name, major)
            Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            val intent2 = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent2)
        }
    }
}