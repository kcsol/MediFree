package com.knu.medifree

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.knu.medifree.adapter.ReservationAdapter
import com.knu.medifree.functions.Doctor
import com.knu.medifree.functions.Doctor.Companion.doctor
import com.knu.medifree.functions.Doctor.Companion.setDoctor
import java.util.*

class DAppActivity : AppCompatActivity() {
    private lateinit var btn_refresh: ImageButton
    private lateinit var btn_date_select : Button
    private lateinit var reservations_listview : ListView
    private lateinit var reservations : List<String>
    private lateinit var adapter : ReservationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_appoint)

        // 객체 할당
        btn_refresh = findViewById<ImageButton>(R.id.d_home_btn_refresh)
        btn_date_select = findViewById<Button>(R.id.d_appoint_btn_selectdate)
        reservations_listview = findViewById<ListView>(R.id.d_appoint_list_reservations)

        //클릭 리스너 할당
        btn_date_select.setOnClickListener {
            val today = GregorianCalendar()
            val year: Int = today.get(Calendar.YEAR)
            val month: Int = today.get(Calendar.MONTH)
            val date: Int = today.get(Calendar.DATE)
            val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    val day = "${year}-${month+1}-${dayOfMonth}"
                    btn_date_select.setText(day)
                    Log.e("date", day)

                    reservations = Doctor.searchDoctorSchedule(day)!!
                    if (!reservations.isEmpty()) {
                        reservations_listview.setVisibility(View.VISIBLE)
                        Log.e("reservations", reservations.toString())

                        adapter = ReservationAdapter(this@DAppActivity, reservations)
                        reservations_listview.adapter = adapter
                    }

                    else {
                        Toast.makeText(this@DAppActivity, "해당 날짜의 예약이 없습니다.", Toast.LENGTH_SHORT).show()
                        reservations_listview.setVisibility(View.INVISIBLE)
                    }
                }
            }, year, month, date)
            dlg.show()

        }

        btn_refresh!!.setOnClickListener {
            // 새로고침 버튼을 눌렀을 때
            // 현재 상황 :
            // TODO :
        }
    }

    private fun findres (name : String, date : String) : ArrayList<String>  {
        //test용
        val reservations = arrayListOf("test1", "test2")
        return reservations
    }
}