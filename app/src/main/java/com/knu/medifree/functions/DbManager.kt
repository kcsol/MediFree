package com.knu.medifree.functions

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DbManager(val context: Context) {
    companion object {
        val TYPE_PATIENT = 0
        val TYPE_DOCTOR = 1
    }


    val mAuth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()


    /*
     * account.signUp(emailString, passwordString)
     * firebase상에 중복된 email이 존재하면 null을 return합니다.
     * email 혹은 password가 양식이 잘못되면 null을 return합니다.
     */
    fun signUp(type:Int, email:String, password:String, name:String, phone:String): String? {

        var uid:String? = null
        // Firebase 계정 생성
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                    task->
                if(task.isSuccessful) {
                    Toast.makeText(context, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()
                    Log.i("register", "성공")

                    // DB 저장
                    uid = mAuth.currentUser!!.uid
                    var user = hashMapOf<String, Any>(
                        "userType" to type,
                        "name" to name,
                        "phone" to phone
                    )

                    Log.i("uid", uid.toString())
                    db.collection("Profile").document(uid.toString()).set(user)
                        .addOnSuccessListener {
                            Log.i("DB", "계정 저장 성공")
                        }
                        .addOnFailureListener {
                            Log.i("DB", "계정 저장 실패")
                        }
                }
                else {
                    Toast.makeText(context, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    Log.i("register", "실패")
                }
            }

        return uid
    }




}