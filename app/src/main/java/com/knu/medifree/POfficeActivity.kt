package com.knu.medifree

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.knu.medifree.adapter.ReservationAdapter
import com.knu.medifree.adapter.ReservationpAdapter
import com.knu.medifree.functions.Patient
import java.util.*
import kotlin.collections.ArrayList

class POfficeActivity : AppCompatActivity() {
    private lateinit var office_btn : Button
    private lateinit var list_reservations : ArrayList<String>
    private lateinit var listView : ListView
    lateinit var reservationnum : String
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_office)

        val today = GregorianCalendar()
        val year: Int = today.get(Calendar.YEAR)
        val month: Int = today.get(Calendar.MONTH)
        val date: Int = today.get(Calendar.DATE)

        val test = "${year}/${month+1}/${date}"
        Log.e("today", test)

        var tmp = ArrayList<String>()
        tmp = Patient.searchPatientReservationList() as ArrayList<String>

        var adapter : ReservationpAdapter = ReservationpAdapter(this, tmp)
        listView = findViewById<ListView>(R.id.listview_p_office_patient)
        listView.adapter = adapter



        office_btn = findViewById<Button>(R.id.p_office_btn)

        Constants.isIntiatedNow = true
        Constants.isCallEnded = true
        //진료실 버튼
        office_btn.setOnClickListener {
            //webrtc 진료 시작
            Toast.makeText(this,Patient.ReservationNum,Toast.LENGTH_LONG).show()
            val intent = Intent(this@POfficeActivity, RTCActivity::class.java)
            intent.putExtra("meetingID",Patient.ReservationNum)// string type의 reservationnum
            intent.putExtra("isJoin",true)
            intent.putExtra("type", RTCActivity.PATIENT)
            startActivity(intent)
            }

    }

    //doctorname, date input -> reservations return
}