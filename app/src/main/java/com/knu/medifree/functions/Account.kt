package com.knu.medifree.functions

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/*
 * 변수명 = Account(this.applicationContext)
 * Context는 Toast를 띄우기 위해 전달됩니다.
 */
class Account(val context: Context) {
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
    fun signUp(email:String, password:String): String? {

        var uid:String? = null
        // Firebase 계정 생성
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                    task->
                if(task.isSuccessful) {
                    Toast.makeText(context, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()
                    Log.i("register", "성공")

                    /*
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
                     */
                }
                else {
                    Toast.makeText(context, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    Log.i("register", "실패")
                }
            }



        return uid
    }

    /*
     * account.signIn(emailString, passwordString)
     * 로그인이 성공하면 Pair(uid, userType)을 반환합니다.
     * 현재는 DB가 구현되지 않아 userType은 항상 null입니다.
     * 로그인이 실패하면 Pair(null, null)을 반환합니다.
     */
    fun signIn(email:String, password:String): Pair<String?, String?> {

        if(email.length <= 0 || password.length <= 0) {
            return Pair(null, null)
        }

        var uid: String? = null
        var userType: String? = null

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                task ->
            if(task.isSuccessful) {
                var user = mAuth.currentUser
                uid = user!!.uid
                Toast.makeText(context, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                Log.i("login", "성공")
                /*
                val docRef = db.collection("Profile").document(uid)
                docRef.get().addOnCompleteListener {
                        task->
                    if(task.isSuccessful) {
                        val doc = task.result!!
                        if (doc.exists()) {
                            val userType = doc.data!!.get("userType").toString()
                            if (userType == "Patient") {
                                //DBManager.initDBManager(uid, TYPE_PATIENT)
                            } else if (userType == "Doctor") {
                                //DBManager.initDBManager(uid, TYPE_DOCTOR)
                            }
                        }
                    }
                }
                 */
            } else {
                Toast.makeText(context, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                Log.i("login", "실패")
            }
        }

        return Pair(uid, userType)
    }

}