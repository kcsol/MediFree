package com.knu.medifree

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.knu.medifree.functions.Account
import com.knu.medifree.functions.Doctor
import com.knu.medifree.functions.Patient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class LoginActivity : AppCompatActivity() {
    private lateinit var et_email: EditText
    private lateinit var et_password:EditText
    private lateinit var btn_signin: ImageButton
    private lateinit var btn_signup: Button

    companion object {
        var loading = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (loading == 0) {
            loading = 1
            intent = Intent(applicationContext, LoadingActivity::class.java)
            startActivity(intent)
        }


        // Assigninge
        btn_signin = findViewById<ImageButton>(R.id.login_btn_signin)
        btn_signup = findViewById<Button>(R.id.login_btn_signup)
        et_email = findViewById<EditText>(R.id.login_et_email)
        et_password = findViewById<EditText>(R.id.login_et_password)


        // Listeners
        btn_signin.setOnClickListener { /* Go PHomeActivity */

            // get string values
            val email = et_email.text.toString()
            val password = et_password.text.toString()

            var isCompleted = false
            val login = Account.signIn(email, password)
            if(login != null) {
                val uid = login.first
                val type = login.second

                if (type == 2)//최초 로그인이 아닐 시
                {
                    startToast("찬솔아 이거 해죠")
                    //Parameter : email, password
                    //return : ID type -> patient : 0
                    //                    doctor : 1
                    //        , name
                } else if (type == 0)//patient
                {
                    val intent =
                        Intent(this@LoginActivity.applicationContext, PHomeActivity::class.java)
                    intent.putExtra("user_id", uid)
                    Patient.setPatient(uid!!)
                    startActivity(intent)
                } else if (type == 1)//doctor
                {
                    val intent =
                        Intent(this@LoginActivity.applicationContext, DHomeActivity::class.java)
                    intent.putExtra("user_id", uid)
                    Doctor.setDoctor(uid!!)
                    startActivity(intent)
                } else {
                    startToast("type error")
                }
            }
            else {
                Toast.makeText(this, "로그인 정보가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        btn_signup.setOnClickListener { // Go TypeActivity
            val intent = Intent(applicationContext, TypeActivity::class.java)
            startActivity(intent)
        }
    }


    private fun startToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}