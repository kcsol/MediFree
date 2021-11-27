package com.knu.medifree

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.knu.medifree.adapter.ReservationAdapter
import java.util.*
import kotlin.collections.ArrayList

class DOfficeActivity : AppCompatActivity() {
    private lateinit var office_btn : Button
    private lateinit var list_reservations : ArrayList<String>
    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_office)

        listView = findViewById(R.id.listview_office_patient)
        val today = GregorianCalendar()
        val year: Int = today.get(Calendar.YEAR)
        val month: Int = today.get(Calendar.MONTH)
        val date: Int = today.get(Calendar.DATE)

        val test = "${year}/${month+1}/${date}"
        Log.e("today", test)

        val doctorname = "유태훈"
        val testdate = "20211126"
        list_reservations = findres(doctorname, testdate)
        var adapter : ReservationAdapter = ReservationAdapter(this, list_reservations)
        listView = findViewById<ListView>(R.id.listview_office_patient)
        listView.adapter = adapter

        office_btn = findViewById<Button>(R.id.d_office_btn)
        //진료실 버튼
        office_btn.setOnClickListener {
        //webrtc 진료 시작
        }
    }

    //doctorname, date input -> reservations return
    private fun findres (name : String, date : String) : ArrayList<String>  {
        //test용
        val reservations = arrayListOf("test1", "test2")
        return reservations
    }
}