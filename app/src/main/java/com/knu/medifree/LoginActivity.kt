package com.knu.medifree

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var et_email: EditText
    private lateinit var et_password:EditText
    private lateinit var btn_signin: ImageButton
    private lateinit var btn_signup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        // Loading
        val intent = Intent(this, TypeActivity::class.java)
        startActivity(intent)


        //DB
//        val mAuth: FirebaseAuth? = null


        // Assigning
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

            // Go in below method
//            signin(email, password)
        }
        btn_signup.setOnClickListener { // Go TypeActivity
            val intent = Intent(applicationContext, TypeActivity::class.java)
            startActivity(intent)
        }
    }

//    // completed
//    private fun signin(email: String, password: String) {
//        if (email.length > 0 && password.length > 0) {
//            mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, object : OnCompleteListener<AuthResult?>() {
//                    fun onComplete(task: Task<AuthResult?>) {
//                        if (task.isSuccessful()) {
//                            // 로그인 성공
//                            val user: FirebaseUser = mAuth.getCurrentUser()
//                            startToast("로그인 되었습니다.")
//                            val uid: String = user.getUid()
//
//                            //Firestore db로 부터 uid를 사용하여 현재 user의 userType을 가져오는 함수.
//                            val db: FirebaseFirestore = FirebaseFirestore.getInstance()
//                            val docRef: DocumentReference = db.collection("Profile").document(uid)
//                            docRef.get().addOnCompleteListener(object :
//                                OnCompleteListener<DocumentSnapshot?>() {
//                                fun onComplete(task: Task<DocumentSnapshot?>) {
//                                    if (task.isSuccessful()) {
//                                        val document: DocumentSnapshot = task.getResult()
//                                        if (document.exists()) {
//                                            val userType: String =
//                                                document.getData().get("userType").toString()
//                                            if (userType == "Patient") {
//                                                //patient일때 patient 홈 화면으로 간다.
//                                                val intent = Intent(
//                                                    applicationContext,
//                                                    PHomeActivity::class.java
//                                                )
//                                                DBManager.initDBManager(uid, User.TYPE_PATIENT)
//                                                DBManager.startActivityWithReservationReading(
//                                                    this@LoginActivity,
//                                                    intent
//                                                )
//                                                // Auto Termination
//                                            } else {
//                                                //Doctor라면 Doctor홈화면으로 간다.
//                                                val intent = Intent(
//                                                    applicationContext,
//                                                    DHomeActivity::class.java
//                                                )
//                                                DBManager.initDBManager(uid, User.TYPE_DOCTOR)
//                                                startActivity(intent)
//                                                finish()
//                                                // Auto Termination
//                                            }
//                                        } else {
//                                            startToast("document가 없습니다.")
//                                        }
//                                    } else {
//                                        startToast("get failed with " + task.getException())
//                                    }
//                                }
//                            })
//                        } else {
//                            // 로그인 실패=> 비밀번호 길이 및 아이디 중복 여부 등
//                            if (task.getException() != null) {
//                                startToast(task.getException().toString())
//                            } else {
//                                startToast("NULL value!")
//                            }
//                        }
//                    }
//                })
//        } else startToast("이메일 또는 비밀번호를 입력해주세요.")
//    } // End of Method

    private fun startToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}