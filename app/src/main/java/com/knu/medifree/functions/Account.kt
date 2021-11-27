package com.knu.medifree.functions

import android.util.Log
import com.google.firebase.auth.FirebaseAuth


class Account {

    companion object {

        val PATIENT = 0
        val DOCTOR = 1

        val mAuth = FirebaseAuth.getInstance()


        /*
         * 모든 작업이 성공적으로 수행되면 uid를 반환합니다.
         * 중복된 email로 signUp을 시도하면 null을 반환합니다.
         * email 혹은 password 양식이 잘못되면 null을 반환합니다.
         * TYPE_PATIENT일 때, address가 없으면 null을 반환합니다.
         */
        fun signUp(userType:Int, email:String, password:String, name:String, phone:String, address:String): String? {
            if (userType == PATIENT && address == "") {
                Log.e("Account.signUp", "환자의 정보에 Address가 빈 값입니다.")
                return null
            }

            var createuserTask = mAuth.createUserWithEmailAndPassword(email, password)
            while (!createuserTask.isComplete) {}
            if (!createuserTask.isSuccessful) {
                Log.w("Account.signUp", "중복된 email 혹은 잘못된 양식의 email/password 입니다.")
                return null
            }
            val uid = createuserTask.result!!.user!!.uid
            
            val user = hashMapOf<String, Any?>(
                "userType" to userType,
                "name" to name,
                "phone" to phone,
                "address" to address,
            )
            DBManager.save(DBManager.PROFILE, uid, user)


            return uid
        }
        fun signUpDoctor(name:String, hospital:String, major:String) {
            DBManager.save(DBManager.DOCTOR, name, hashMapOf())

            DBManager.add(DBManager.HOSPITAL, hospital, major, name)
            DBManager.add(DBManager.MAJOR, major, "병원명", hospital)
        }

        /*
         * 로그인이 성공하면 Pair(uid, userType)을 반환합니다.
         * 로그인이 실패하면 Pair(null, null)을 반환합니다.
         */
        fun signIn(email:String, password:String): Pair<String?, Int?>? {

            if (email.length <= 0 || password.length <= 0) {
                Log.e("Account.signIn", "잘못된 email 혹은 password가 전달되었습니다.")
                return null
            }


            var signinTask = mAuth.signInWithEmailAndPassword(email, password)
            while (!signinTask.isComplete) {}
            if(!signinTask.isSuccessful) {
                Log.w("Account.signIn", "로그인 실패")
                return null
            }
            val uid = signinTask.result!!.user!!.uid

            val profile = DBManager.load(DBManager.PROFILE, uid)
            if(profile == null) {
                Log.e("Account.signIn", "해당 계정의 프로필이 DB에 존재하지 않습니다.")
                return null
            }
            val userType = profile!!["userType"].toString().toInt()


            return Pair(uid, userType)
        }

    }

}