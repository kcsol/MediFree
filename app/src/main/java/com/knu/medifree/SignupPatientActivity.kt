package com.knu.medifree

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception
import java.util.HashMap
import com.knu.medifree.functions.Account
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SignupPatientActivity : AppCompatActivity() {
    //    private var mAuth: FirebaseAuth? = null
    private lateinit var user_id: String
    private lateinit var btn_reg: ImageButton
    private lateinit var et_email: EditText
    private lateinit var et_password: EditText
    private lateinit var et_password_again: EditText
    private lateinit var et_name: EditText
    private lateinit var et_tel: EditText
    private lateinit var et_address: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_patient)
        //mAuth = FirebaseAuth.getInstance()
        btn_reg = findViewById<ImageButton>(R.id.signup_patient_btn_reg)
        et_email = findViewById<EditText>(R.id.signup_patient_email)
        et_password = findViewById<EditText>(R.id.signup_patient_password)
        et_password_again = findViewById<EditText>(R.id.signup_patient_password_again)
        et_name = findViewById<EditText>(R.id.signup_patient_name)
        et_tel = findViewById<EditText>(R.id.signup_patient_tel)
        et_address = findViewById<EditText>(R.id.signup_patient_address)

        // 클릭 리스너 할당
        btn_reg.setOnClickListener { // 계정 생성후 PHome으로 이동
//            createAccount_Patient()
            CoroutineScope(Dispatchers.IO).launch {
                var uid:String? = null
                val signUpMethod = async {
                    uid = Account.signUp(Account.PATIENT,
                        et_email.text.toString(), et_password.text.toString(),
                        et_name.text.toString(), et_tel.text.toString(), et_address.text.toString())
                }
                signUpMethod.await()
                Log.i("signUp", uid.toString())
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        //val currentUser: FirebaseUser = mAuth.getCurrentUser()
    }

//    private fun createAccount_Patient() {
//        val email = (findViewById<View>(R.id.signup_patient_email) as TextView).text.toString()
//        val password =
//            (findViewById<View>(R.id.signup_patient_password) as TextView).text.toString()
//        val passwordCheck =
//            (findViewById<View>(R.id.signup_patient_password_again) as TextView).text.toString()
//        if (email.length > 0 && password.length > 0 && passwordCheck.length > 0) {
//            if (password == passwordCheck) {
//                mAuth.createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(this, object : OnCompleteListener<AuthResult?>() {
//                        fun onComplete(task: Task<AuthResult?>) {
//                            if (task.isSuccessful()) {
//                                // 회원가입 성공
//                                val user: FirebaseUser = mAuth.getCurrentUser()
//                                startToast("회원가입이 완료되었습니다.")
//
//                                //현재 유저의 uid가져오기.
//                                user_id = user.getUid()
//                                insert_user_Information(user_id)
//                            } else {
//                                // 회원가입 실패=> 비밀번호 길이 및 아이디 중복 여부 등
//                                if (task.getException() != null) {
//                                    startToast(task.getException().toString())
//                                }
//                            }
//                        }
//                    })
//            } else {
//                // 비밀번호 확인실패.
//                startToast("비밀번호가 일치하지 않습니다.")
//            }
//        } else {
//            startToast("이메일 또는 비밀번호를 입력해주세요.")
//        }
//    }

    //알림을 출력하는 method
    private fun startToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    //생성된 uid 및 나머지 정보들 firestore에 넣는 작업.
    private fun insert_user_Information(uid: String?) {
        val name = (findViewById<EditText>(R.id.signup_patient_name)).text.toString()
        val phone = (findViewById<EditText>(R.id.signup_patient_tel)).text.toString()
        val address = (findViewById<EditText>(R.id.signup_patient_address)).text.toString()
        //val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["userType"] = "Patient"
        user["name"] = name
        user["phoneNum"] = phone
        user["Address"] = address

        //실제 firestore에 추가하는 작업, add=> 자동으로 문서id(문서이름)를 만들어줌

        // Add a new document with a generated ID
//        db.collection("Profile").document(uid)
//            .set(user)
//            .addOnSuccessListener(object : OnSuccessListener<Void?>() {
//                fun onSuccess(avoid: Void?) {
//                    //홈화면으로 이동.
//                    val intent = Intent(applicationContext, PHomeActivity::class.java)
//                    DBManager.initDBManager(uid, User.TYPE_PATIENT)
//                    DBManager.startActivityWithReservationReading(
//                        this@SignupPatientActivity,
//                        intent
//                    )
//
//                    // Addition
//                    val act_login = LoginActivity.activity as LoginActivity
//                    act_login.finish() // Login activity termination
//                    // End of Addition
//                    finish()
//                }
//            })
//            .addOnFailureListener(object : OnFailureListener() {
//                fun onFailure(e: Exception) {
//                    startToast("정보저장에 실패하였습니다.")
//                }
//            })
    }
}