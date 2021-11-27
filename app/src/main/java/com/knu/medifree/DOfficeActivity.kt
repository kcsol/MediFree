package com.knu.medifree

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.knu.medifree.adapter.ReservationAdapter
import com.knu.medifree.functions.Doctor
import java.util.*
import kotlin.collections.ArrayList

class DOfficeActivity : AppCompatActivity() {
    private lateinit var office_btn: Button
    private lateinit var reservations: List<String>
    private lateinit var reservations_listview: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_office)

        reservations_listview = findViewById(R.id.listview_office_patient)
        office_btn = findViewById<Button>(R.id.d_office_btn)

        //오늘 날짜 가져오기
        val today = GregorianCalendar()
        val year: Int = today.get(Calendar.YEAR)
        val month: Int = today.get(Calendar.MONTH)
        val date: Int = today.get(Calendar.DATE)
        val day = "${year}-${month + 1}-${date}" //오늘 날짜

        //오늘 날짜에 해당하는 예약 가져오기
        reservations = Doctor.searchDoctorSchedule(day)!!
        var adapter: ReservationAdapter = ReservationAdapter(this, reservations)
        reservations_listview.adapter = adapter

        if (!reservations.isEmpty()) {
            reservations_listview.setVisibility(View.VISIBLE)
            Log.e("reservations", reservations.toString())

            adapter = ReservationAdapter(this@DOfficeActivity, reservations)
            reservations_listview.adapter = adapter
        }

        else {
            Toast.makeText(this@DOfficeActivity, "오늘은 예약이 없습니다.", Toast.LENGTH_SHORT).show()
            reservations_listview.setVisibility(View.INVISIBLE)
        }


        //진료 시작할 예약 리스트뷰에서 선택하기
        var resnum : String
        reservations_listview.setOnItemClickListener { adapterView, view, position, id ->
            resnum = adapter.getItem(position).toString()
            Log.e("진료 시작할 예약 num", resnum)
        }

        //진료실 버튼
        office_btn.setOnClickListener {
            //webrtc 진료 시작
        }
    }
}