package com.knu.medifree

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
//import com.knu.medifree.model.Patient
//import com.knu.medifree.model.PatientAdapter
//import com.knu.medifree.model.PatientAdapter2


class DetailAppActivity : AppCompatActivity() {
    private var time: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_detailapp)
        val intent = intent
        time = intent.getStringExtra("time")
        val textView = findViewById<View>(R.id.d_dtailapp_time) as TextView
        textView.text = intent.getStringExtra("time") + " is selected"
        //populatePatientsList()
    }

//    private fun populatePatientsList() {
//        val arrayOfUsers: ArrayList<*> = Patient.getPatientFromTime(time)
//        // Create the adapter to convert the array to views
//        val adapter = PatientAdapter2(this, arrayOfUsers)
//        // Attach the adapter to a ListView
//        val listView = findViewById<View>(R.id.listview_patientlist) as ListView
//        listView.adapter = adapter
//    }
}
