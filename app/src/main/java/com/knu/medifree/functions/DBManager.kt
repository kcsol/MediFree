package com.knu.medifree.functions

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore


class DBManager {

    companion object {

        val PROFILE = 0
        val HOSPITAL = 1
        val DOCTOR = 2
        val MAJOR = 3
        val RESERVATION = 4
        // 필요한 Data Type 추가

        val DB = FirebaseFirestore.getInstance()


        fun convertType(objectType:Int): String? {
            var collection:String
            when(objectType) {
                PROFILE -> collection = "Profile"
                HOSPITAL -> collection = "hospital"
                DOCTOR -> collection = "doctor"
                MAJOR -> collection = "Major"
                RESERVATION -> collection = "Reservation"
                // 필요한 Data Type 추가

                else -> {
                    Log.e("DBManager.load", "잘못된 type이 전달되었습니다.")
                    return null
                }
            }

            return collection
        }

        fun save(objectType:Int, id:String, obj:HashMap<String, Any?>) {
            val collection = convertType(objectType)

            DB.collection(collection!!).document(id).set(obj)
        }

        fun saveWithoutID(objectType:Int, obj:HashMap<String, Any?>): String? {
            val collection = convertType(objectType)

            val docRef = DB.collection(collection!!).document()
            var task = docRef.set(obj)
            while (!task.isComplete) {}
            if(!task.isSuccessful) {
                Log.e("DBManager.saveWithoutID", "문서 생성에 실패했습니다.")
                return null
            }
            val id = docRef.id


            return id
        }

        fun load(objectType:Int, id:String): Map<String, Any>? {
            val collection = convertType(objectType)

            val docRef = DB.collection(collection!!).document(id)
            val task = docRef.get()
            while(!task.isComplete) {}
            val doc = task.result!!.data


            return doc
        }

        fun update(objectType:Int, id:String, field:String, new:Any) {
            val collection = convertType(objectType)

            DB.collection(collection!!).document(id)
                .update(
                    field, new
                )
        }

        fun add(objectType:Int, id:String, field:String, new:String) {
            val collection = convertType(objectType)

            val doc = load(objectType, id)
            try {
                val newField = doc!![field] as MutableList<String>
                for(i in newField) {
                    if(i == new)
                        return
                }
                newField.add(new)
                DB.collection(collection!!).document(id)
                    .update(
                        field, newField
                    )
            }
            catch (e:Exception) {
                Log.e("DBManager.add", "추가하려는 Field가 배열이 아닙니다. Field를 새로 생성합니다.")

                DB.collection(collection!!).document(id).update(field, listOf(new))
            }
        }

        fun delete(objectType:Int, id:String) {
            val collection = convertType(objectType)

            DB.collection(collection!!).document(id).delete()
        }

    }
}