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
            val uid = Account.signUp(Account.DOCTOR, et_email.text.toString(), et_password.text.toString(), et_name.text.toString(), et_tel.text.toString(), "")
            Log.e("doctor signup" , uid.toString())
            val intent = Intent(applicationContext, SignupDoctor2Activity::class.java)
            startActivity(intent)
        }
    }

    //알림을 출력하는 method
    private fun startToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
//
//    private fun registerUser() {
//        val email = (findViewById<EditText>(R.id.email_D)).text.toString()
//        val password = (findViewById<EditText>(R.id.password_D)).text.toString()
//        val passwordCheck = (findViewById<EditText>(R.id.passwordCheck_D)).text.toString()
//        if (TextUtils.isEmpty(email)) {
//            Toast.makeText(this, "Email을 입력해 주세요.", Toast.LENGTH_SHORT).show()
//            return
//        }
//        if (TextUtils.isEmpty(password)) {
//            Toast.makeText(this, "Password를 입력해 주세요.", Toast.LENGTH_SHORT).show()
//        }
//        if (TextUtils.isEmpty(passwordCheck)) {
//            Toast.makeText(this, "Check Password를 입력해 주세요.", Toast.LENGTH_SHORT).show()
//        }
////        if (password == passwordCheck) {
////            val db: FirebaseFirestore = FirebaseFirestore.getInstance()
////            mAuth.createUserWithEmailAndPassword(email, password)
////                .addOnCompleteListener(object : OnCompleteListener<AuthResult?>() {
////                    fun onComplete(task: Task<AuthResult?>) {
////                        if (task.isSuccessful()) {
////                            // 회원가입 성공
////                            val user: FirebaseUser = mAuth.getCurrentUser()
////
////                            //현재 유저의 uid가져오기.
////                            val uid: String = user.getUid()
////                            //user정보를 db에 집어넣가.
////                            insert_user_Information(uid)
////                        } else {
////                            // 회원가입 실패=> 비밀번호 길이 및 아이디 중복 여부 등
////                            if (task.getException() != null) {
////                                startToast(task.getException().toString())
////                            }
////                        }
////                    }
////                })
////        }
//    }

    //생성된 uid 및 나머지 정보들 firestore에 넣는 작업.
//    private fun insert_user_Information(uid: String) {
//        val name = (findViewById<EditText>(R.id.name_D)).text.toString()
//        val phone = (findViewById<EditText>(R.id.phone_D)).text.toString()
//        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
//        val user: MutableMap<String, Any> = HashMap()
//        user["name"] = name
//        user["userType"] = "Doctor"
//        user["phoneNum"] = phone
//        //실제 firestore에 추가하는 작업, add=> 자동으로 문서id(문서이름)를 만들어줌
//
//        // Add a new document with a generated ID
//        db.collection("Profile").document(uid)
//            .set(user)
//            .addOnSuccessListener(object : OnSuccessListener<Void?>() {
//                fun onSuccess(avoid: Void?) {
//                    //uid정보에 다음 페이지로 넘어가 hospital & major에 넣기.
//                    val intent = Intent(applicationContext, SignupDoctor2Activity::class.java)
//                    intent.putExtra("user_id", uid)
//                    intent.putExtra("name", name)
//                    intent.putExtra("phone", phone)
//                    DBManager.startActivityWithHospitalReading(this@SignupDoctorActivity, intent)
//                    // Auto termination
//                }
//            })
//            .addOnFailureListener(object : OnFailureListener() {
//                fun onFailure(e: Exception) {
//                    startToast("정보저장에 실패하였습니다.")
//                }
//            })
//    }
//
//    fun onClick(v: View) {
//        if (v === btn_next) {
//            //TODO
//            registerUser()
//        }
//    }
}