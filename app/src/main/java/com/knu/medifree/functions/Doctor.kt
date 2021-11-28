package com.knu.medifree.functions

import android.util.Log

class Doctor {

    companion object {
        lateinit var doctor:Map<String, Any>
        fun setDoctor(uid:String) {
            doctor = DBManager.load(DBManager.PROFILE, uid)!!
        }


        fun searchDoctorToWrite(): List<String>?{
            val toWrite = DBManager.load(DBManager.DOCTOR, doctor["name"].toString())!!["작성할것"] as MutableList<String>
            if (toWrite == null)
                return listOf()
            for (i in 0.. toWrite.size - 1) {
                toWrite.remove("")
            }

            return toWrite
        }

        fun searchDoctorToConfirm(): List<String>? {
            val toConfirm = DBManager.load(DBManager.DOCTOR, doctor["name"].toString())!!["확인할것"] as MutableList<String>
            if (toConfirm == null)
                return listOf()
            for (i in 0..toConfirm.size -1) {
                toConfirm.remove("")
            }

            return toConfirm
        }

        fun searchDoctorSchedule(date:String): List<String>? {
            val schedule = DBManager.load(DBManager.DOCTOR, doctor["name"].toString())!!
            if(schedule.contains(date)) {
                var schedule_ = schedule[date] as MutableList<String>
                schedule_.remove("")
                schedule_.remove("")
                schedule_.remove("")
                schedule_.remove("")

                return schedule_
            }
            else
                return emptyList()
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
            DBManager.update(DBManager.RESERVATION, reservNum, "is_completed", 3)

            val toConfirm = searchDoctorToConfirm() as MutableList<String>
            toConfirm.remove(reservNum)

            DBManager.update(DBManager.DOCTOR, doctor["name"] as String, "확인할것", toConfirm)
        }
    }

}