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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        intent = Intent(applicationContext, LoadingActivity::class.java)
        startActivity(intent)


        // Loading
        //val intent = Intent(this, TypeActivity::class.java)
        //startActivity(intent)


        //DB
//        val mAuth: FirebaseAuth? = null


        // Assigninge
        btn_signin = findViewById<ImageButton>(R.id.login_btn_signin)
        btn_signup = findViewById<Button>(R.id.login_btn_signup)
        et_email = findViewById<EditText>(R.id.login_et_email)
        et_password = findViewById<EditText>(R.id.login_et_password)

        // Listeners


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
                    Log.i("doctor", "doctor")
                    val intent =
                        Intent(this@LoginActivity.applicationContext, DHomeActivity::class.java)
                    intent.putExtra("user_id", uid)
                    Doctor.setDoctor(uid!!)
                    startActivity(intent)
                    startToast("doctor!")
                } else {
                    startToast("type error")
                }
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