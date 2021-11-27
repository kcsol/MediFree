package com.knu.medifree

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.knu.medifree.adapter.PatientAdapter
import com.knu.medifree.adapter.ReservationAdapter
import java.util.*
import kotlin.collections.ArrayList


class PConfirmActivity : AppCompatActivity() {
    private lateinit var office_btn : Button
    private lateinit var list_reservations : ArrayList<String>
    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_res_check)

        val today = GregorianCalendar()
        val year: Int = today.get(Calendar.YEAR)
        val month: Int = today.get(Calendar.MONTH)
        val date: Int = today.get(Calendar.DATE)

        val test = "${year}/${month+1}/${date}"
        Log.e("today", test)

        val patient_name = "조성민"
        val testdate = "20211126"
        list_reservations = findres(patient_name, testdate)
        var adapter : ReservationAdapter = ReservationAdapter(this, list_reservations)
        listView = findViewById<ListView>(R.id.listview_myrequest)
        listView.adapter = adapter

    }

    //doctorname, date input -> reservations return
    private fun findres (name : String, date : String) : ArrayList<String>  {
        //test용
        val reservations = arrayListOf("test1", "test2")
        return reservations
    }
}