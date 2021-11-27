package com.knu.medifree.functions

import android.util.Log


class Patient {

    companion object {
        val MAJOR_INNER = 0
        val MAJOR_EARNOSE = 1
        val MAJOR_MENTAL = 2
        val MAJOR_SKIN = 3
        // 필요한 MAJOR 추가, naming


        lateinit var uid:String
        lateinit var patient:Map<String, Any>
        fun setPatient(uid:String) {
            this.uid = uid
            patient = DBManager.load(DBManager.PROFILE, uid)!!
        }


        fun convertMajor(majorType:Int): String? {
            var major:String
            when(majorType) {
                MAJOR_INNER -> major = "내과"
                MAJOR_EARNOSE -> major = "이비인후과"
                MAJOR_MENTAL -> major = "정신과"
                MAJOR_SKIN -> major = "피부과"
                // 필요한 MAJOR 추가, naming

                else -> {
                    Log.e("Patient.searchHospital", "잘못된 major가 전달되었습니다.")
                    return null
                }
            }

            return major
        }

        fun searchHospital(majorType:Int): List<String>? {
            val major = convertMajor(majorType)
            val hospitals = DBManager.load(DBManager.MAJOR, major!!)!!["병원명"] as List<String>

            return hospitals
        }

        fun searchDoctorInHospital(hospital:String, majorType:Int): List<String>? {
            val major = convertMajor(majorType)
            val doctors = DBManager.load(DBManager.HOSPITAL, hospital)!![major] as List<String>

            return doctors
        }

        fun searchDoctorSchedule(doctor:String, date:String): List<Boolean>? {
            val schedules = DBManager.load(DBManager.DOCTOR, doctor)

            // 몇 개로 나눌건지에 따라 list 길이랑 for문 반복수 늘리기
            var selectable = mutableListOf(false, false, false, false)
            val schedule = schedules!![date] as List<String>
            for (i in 0..3) {
                if(schedule[i] == null)
                    selectable[i] = true
            }

            return selectable
        }

        fun searchPatientReservationList(): List<String>? {
            return patient!!["예약번호"] as List<String>
        }

        fun addNewReservation(doctorName:String, date:String, time:Int) {
            val reservation = hashMapOf(
                "diagnosis_date" to date,
                "diagnosis_time" to time,
                "diagnosis_type" to 0,
                "doctor_name" to doctorName,
                "patient_name" to patient!!["name"],
                "is_completed" to 0,
                "room_open" to 0,
                "질문" to listOf("", "", "", ""),
                "답변" to listOf("", "", "", "")
            )
            val reservNum = DBManager.saveWithoutID(DBManager.RESERVATION, reservation)!!

            val doctor = DBManager.load(DBManager.DOCTOR, doctorName)
            var dReservList:MutableList<String>
            try {
                dReservList = doctor!![date] as MutableList<String>
            } catch (e:Exception) {
                dReservList = mutableListOf<String>("", "", "", "")
            }
            dReservList[time] = reservNum

            DBManager.update(DBManager.DOCTOR, doctorName, date, dReservList)
            DBManager.add(DBManager.DOCTOR, doctorName, "작성할것", reservNum)
            DBManager.add(DBManager.PROFILE, uid, "예약번호", reservNum)
        }

        fun searchReservationInfo(reservNum:String): Map<String, Any?>? {
            return DBManager.load(DBManager.RESERVATION, reservNum)
        }

        fun addAnswer(reservNum:String, doctorName:String, answers:List<String>) {
            DBManager.update(DBManager.RESERVATION, reservNum, "답변", answers)
            DBManager.update(DBManager.RESERVATION, reservNum, "is_completed", 2)
            DBManager.add(DBManager.DOCTOR, doctorName, "확인할것", reservNum)
        }
    }

}