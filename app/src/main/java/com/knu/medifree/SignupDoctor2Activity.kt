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
import java.lang.Exception
import java.util.ArrayList
import java.util.HashMap

//package com.knu.medifree
//import com.google.android.gms.tasks.OnFailureListener
//import com.google.android.gms.tasks.OnSuccessListener
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.firestore.FieldValue
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.Query
//import com.knu.medifree.model.Hospital
//import com.knu.medifree.model.User
//import com.knu.medifree.util.DBManager

class SignupDoctor2Activity<database> : AppCompatActivity() {
    private lateinit var btn_reg: ImageButton
    //private var mAuth: FirebaseAuth? = null
    private lateinit var hospitalNameSpinner: Spinner
    private lateinit var majorSpinner: Spinner
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var hospital_name : String
    private lateinit var major : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_doctor_next)
        //mAuth = FirebaseAuth.getInstance()

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
        val majorSpinner = findViewById<View>(R.id.major) as Spinner
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
            val uid = intent.getStringExtra("uid")

            //병원정보 저장 함수 need
            Log.i("병원 정보저장 성공", hospital_name + ", " + major)
            val intent = Intent(applicationContext, DHomeActivity::class.java)
            startActivity(intent)
        }

        // 클릭 리스너 할당
//        btn_reg!!.setOnClickListener { // 회원 가입 버튼을 눌렀을 때
//            // 현재 상황 : DHomeActivity로 이동
//            val db: FirebaseFirestore = FirebaseFirestore.getInstance()
//            val user: FirebaseUser = mAuth.getCurrentUser()
//            val uid: String = user.getUid()
//            insert_user_Information(uid)
//            val intent = intent
//            finish()
//            DBManager.initDBManager(intent.getStringExtra("user_id"), User.TYPE_DOCTOR)
//            startActivity(Intent(applicationContext, DHomeActivity::class.java))
//        }
    }

//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser: FirebaseUser = mAuth.getCurrentUser()
//    }

    //알림을 출력하는 method
    private fun startToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    //생성된 uid 및 나머지 정보들 firestore에 넣는 작업.
//    private fun insert_user_Information(uid: String) {
//        val hospital_Name =
//            (findViewById<View>(R.id.hospital_Name) as Spinner).selectedItem.toString()
//        val major = (findViewById<View>(R.id.major) as Spinner).selectedItem.toString()
//        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
//        val intent = intent
//        val name = intent.getStringExtra("name")
//        val phone = intent.getStringExtra("phone")
//        val user: MutableMap<String, Any?> = HashMap()
//        user["userType"] = "Doctor"
//        user["name"] = name
//        user["phoneNum"] = phone
//        user["Hospital_Name"] = hospital_Name
//        user["Major"] = major
//
//        //실제 firestore에 추가하는 작업, add=> 자동으로 문서id(문서이름)를 만들어줌
//
//        // Add a new document with a generated ID
//        db.collection("Profile").document(uid)
//            .set(user)
//            .addOnSuccessListener(object : OnSuccessListener<Void?>() {
//                fun onSuccess(avoid: Void?) {
//                    //uid정보를 hospital에 넣기.
//                    insert_doctor_to_hospital(uid)
//                }
//            })
//            .addOnFailureListener(object : OnFailureListener() {
//                fun onFailure(e: Exception) {
//                    startToast("정보저장에 실패하였습니다1.")
//                }
//            })
//    }

//    private fun insert_doctor_to_hospital(uid: String) {
//        val hospital_Name =
//            (findViewById<View>(R.id.hospital_Name) as Spinner).selectedItem.toString()
//        val major = (findViewById<View>(R.id.major) as Spinner).selectedItem.toString()
//        val hospital_id: String = DBManager.getHospitalId(hospital_Name)
//        if (hospital_id == null) {
//            // There is not corresponding hospital in DB.
//            DBManager.createHospital(Hospital(hospital_Name, Hospital.getBitmaskByMajorTag(major)))
//        } else {
//            val hospitals: ArrayList<Hospital> = DBManager.getHospitals()
//            for (i in hospitals.indices) {
//                if (hospital_id == hospitals[i].getHospitalId()) {
//                    var major_bit_mask: Int = hospitals[i].getBitmask()
//                    if (major_bit_mask and Hospital.getBitmaskByMajorTag(major) !== 0) return else {
//                        DBManager.deleteHospital(hospital_id)
//                        major_bit_mask += Hospital.getBitmaskByMajorTag(major)
//                        DBManager.createHospital(Hospital(hospital_Name, major_bit_mask))
//                    }
//                }
//            }
//        }
//    }

    companion object {
        const val EXTRA_ADDRESS = "address"
    }
}