package com.knu.medifree.functions

import android.util.Log

class Doctor {

    companion object {
        lateinit var doctor:Map<String, Any>
        fun setDoctor(uid:String) {
            doctor = DBManager.load(DBManager.PROFILE, uid)!!
        }


        fun searchDoctorToWrite(): List<String>?{
            val toWrite = DBManager.load(DBManager.DOCTOR, doctor["name"].toString())!!["작성할것"] as List<String>

            return toWrite
        }

        fun searchDoctorToConfirm(): List<String>? {
            val toConfirm = DBManager.load(DBManager.DOCTOR, doctor["name"].toString())!!["확인할것"] as List<String>

            return toConfirm
        }

        fun searchDoctorSchedule(date:String): List<String>? {
            val schedule = DBManager.load(DBManager.DOCTOR, doctor["name"].toString())!![date] as List<String>

            return schedule
        }

        fun searchReservationInfo(reservNum:String): Map<String, Any?>? {
            return DBManager.load(DBManager.RESERVATION, reservNum)
        }

        fun addQuestion(reservNum:String, doctorName:String, questions:List<String>) {
            DBManager.update(DBManager.RESERVATION, reservNum, "질문", questions)
            DBManager.update(DBManager.RESERVATION, reservNum, "is_completed", 1)

            val toWrite = searchDoctorToWrite() as MutableList<String>
            toWrite.remove(reservNum)

            DBManager.update(DBManager.DOCTOR, doctorName, "작성할것", toWrite)
        }

        fun addDiagnosisType(reservNum: String, diagnosisType: Int) {
            DBManager.update(DBManager.RESERVATION, reservNum, "diagnosis_type", diagnosisType)

            val toConfirm = searchDoctorToConfirm() as MutableList<String>
            toConfirm.remove(reservNum)

            DBManager.update(DBManager.DOCTOR, doctor["name"] as String, "확인할것", toConfirm)
        }
    }

}