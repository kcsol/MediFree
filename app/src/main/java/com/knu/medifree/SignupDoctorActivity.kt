package com.knu.medifree

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.knu.medifree.functions.Account
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SignupDoctorActivity : AppCompatActivity() /*, View.OnClickListener*/ {
    lateinit var btn_next: ImageButton
    private lateinit var et_email: EditText
    private lateinit var et_password: EditText
    private lateinit var et_password_again: EditText
    private lateinit var et_name: EditText
    private lateinit var et_tel: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_doctor)

        // 객체 할당
        btn_next = findViewById<ImageButton>(R.id.next_step)
        et_email = findViewById<EditText>(R.id.signup_doctor_email)
        et_password = findViewById<EditText>(R.id.signup_doctor_password)
        et_password_again = findViewById<EditText>(R.id.signup_doctor_password_again)
        et_name = findViewById<EditText>(R.id.signup_doctor_name)
        et_tel = findViewById<EditText>(R.id.signup_doctor_tel)

        // 클릭 리스너 할당
        btn_next.setOnClickListener {
            val uid = Account.signUp(
                Account.DOCTOR,
                et_email.text.toString(),
                et_password.text.toString(),
                et_name.text.toString(),
                et_tel.text.toString(),
                ""
            )
            Log.e("doctor signup", uid.toString())
            val intent = Intent(applicationContext, SignupDoctor2Activity::class.java)
            intent.putExtra("name", et_name.text.toString())
            startActivity(intent)
        }
    }
}
