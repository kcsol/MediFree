package com.knu.medifree

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.knu.medifree.adapter.ConfAdapter
import com.knu.medifree.adapter.ReservationAdapter
import com.knu.medifree.functions.Patient
import java.util.*
import kotlin.collections.ArrayList


class PConfirmActivity : AppCompatActivity() {
    private lateinit var office_btn : Button
    private lateinit var list_reservations : ArrayList<String>
    private lateinit var listView : ListView
    lateinit var name :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_res_check)

        var intent = getIntent()
        var user_name = intent.getStringExtra("name")

        val today = GregorianCalendar()
        val year: Int = today.get(Calendar.YEAR)
        val month: Int = today.get(Calendar.MONTH)
        val date: Int = today.get(Calendar.DATE)

        val test = "${year}/${month+1}/${date}"
        Log.e("today", test)


        var tmp = ArrayList<String>()
        tmp = Patient.searchPatientReservationList() as ArrayList<String>
//        val db : Map<String, Any?>
//        var user_date : String
//        db = Patient.searchReservationInfo(user_name!!)!!
//        Log.e("date", db["diagnosis_date"].toString())
//        user_date = db["diagnosis_date"].toString()


        var adapter : ConfAdapter = ConfAdapter(this, tmp)
        listView = findViewById<ListView>(R.id.listview_myrequest)
        listView.adapter = adapter

    }

    //doctorname, date input -> reservations return
}