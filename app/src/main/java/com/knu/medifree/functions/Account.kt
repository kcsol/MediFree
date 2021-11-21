package com.knu.medifree.functions

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class Account {
    companion object {
        val TYPE_PATIENT = 0
        val TYPE_DOCTOR = 1

        val mAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()


        /*
         * Usage:
         CoroutineScope(Dispatchers.IO).launch {
                    var uid:String? = null
                    val signUpMethod = async { uid = Account.signUp(Account.TYPE_?, email, password, name, phone, address) }
                    signUpMethod.await()
                    Log.i("signUp", uid.toString())
                }
         * 모든 작업이 성공적으로 수행되면 uid:String을 반환합니다.
         * firebase상에 중복된 email이 존재하면 null을 반환합니다.
         * email 혹은 password가 양식이 잘못되면 null을 반환합니다.
         * TYPE_PATIENT일 때, address가 없으면 null을 반환합니다.
         */
        suspend fun signUp(type:Int, email:String, password:String, name:String, phone:String, address:String): String? {

            if(type == TYPE_PATIENT && address == null) {
                return null
            }

            var uid:String? = null

            // Firebase 계정 생성
            var task: Task<AuthResult?>? = null
            val createUserMethod = CoroutineScope(Dispatchers.IO).async {
                task = mAuth.createUserWithEmailAndPassword(email, password)
                while (!task!!.isSuccessful) {}
            }
            createUserMethod.await()

            if(task!!.result!!.user == null) {
                Log.w("Register", "실패")
                return null
            }

            //Toast.makeText(context, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()
            Log.i("register", "성공")

            // DB 저장
            uid = mAuth.currentUser!!.uid
            var user = hashMapOf<String, Any>(
                "userType" to type,
                "name" to name,
                "phone" to phone,
                "address" to address
            )

            Log.i("uid", uid.toString())
            val setDocMethod = CoroutineScope(Dispatchers.IO).async {
                db.collection("Profile").document(uid.toString()).set(user)
            }
            setDocMethod.await()

            Log.i("DB", "계정 저장 성공")


            return uid
        }


        /*
         * Usage:
         CoroutineScope(Dispatchers.IO).launch {
                    var pair:Pair<String?, Int?> = Pair<String?, Int?>(null, null)
                    val signInMethod = async { pair = Account.signIn(email, password) }
                    signInMethod.await()
                    Log.i("signIn", pair.toString())
                }
         * 로그인이 성공하면 Pair(uid, userType)을 반환합니다.
         * 로그인이 실패하면 Pair(null, null)을 반환합니다.
         */
        suspend fun signIn(email:String, password:String): Pair<String?, Int?> {

            if (email.length <= 0 || password.length <= 0) {
                return Pair(null, null)
            }

            var uid: String? = null
            var userType: Int? = null

            try {
                var task: Task<AuthResult?>? = null
                val signInMethod = CoroutineScope(Dispatchers.IO).async {
                    task = mAuth.signInWithEmailAndPassword(email, password)
                    while (!task!!.isSuccessful) {}
                }
                signInMethod.await()

                if (task == null || task!!.result!!.user == null) {
                    Log.w("Login", "실패")
                    return Pair(null, null)
                }

                val auth = task!!.result!!
                var user = auth.user
                uid = user!!.uid
                //Toast.makeText(context, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                Log.i("Login", "성공")
            } catch(e:Exception) {
                Log.e("Login", "실패")
            }

            var task:Task<DocumentSnapshot?>? = null
            val getDocMethod = CoroutineScope(Dispatchers.IO).async {
                val docRef = db.collection("Profile").document(uid.toString())
                task = docRef.get()
                while (!task!!.isSuccessful) {}
            }
            getDocMethod.await()

            if(task == null || !task!!.isSuccessful) {
                Log.w("DB", "docRef.get() 실패")
                return Pair(null, null)
            }

            val doc = task!!.result!!
            if (doc.exists()) {
                val NUserType = doc.data!!["userType"].toString().toInt()
                if (NUserType == TYPE_PATIENT) {
                    Log.i("type", "patient")
                    userType = TYPE_PATIENT
                } else if (NUserType == TYPE_DOCTOR) {
                    Log.i("type", "doctor")
                    userType = TYPE_DOCTOR
                }
            } else {
                Log.w("DB", "docRef.get() 실패")
                return Pair(null, null)
            }


            return Pair(uid, userType)
        }
    }




}