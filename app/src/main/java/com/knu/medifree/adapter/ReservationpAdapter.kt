package com.knu.medifree.adapter

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.knu.medifree.POfficeActivity
import com.knu.medifree.R
import com.knu.medifree.functions.Patient
import com.knu.medifree.functions.Patient.Companion.ReservationNum
import java.util.ArrayList

class ReservationpAdapter(context: Context?, reservations: ArrayList<String>) :
    BaseAdapter() {
    var mContext: Context? = null
    var mLayoutInflater: LayoutInflater? = null
    var sample: ArrayList<String>

    override fun getCount(): Int {
        return sample.size
    }

    override fun getItem(position: Int): Any {
        return sample[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = mLayoutInflater!!.inflate(R.layout.res_check_item, null)
        val doc_name = view.findViewById<TextView>(R.id.office_p_doctor)
        val time = view.findViewById<TextView>(R.id.office_p_untact_time)
        val date = view.findViewById<TextView>(R.id.office_p_untact_date)
        val btn = view.findViewById<Button>(R.id.btn_on)
        //DB에서 각 reservation에 대한 환자이름, 시간 load
        val test : Map<String, Any?>
//        val patient = Patient("qzngwoSZNafRWnnH4QaCy9nz3ft1") // -> reservation으로 변경해야함
//        Log.e("laod", "DBLoad")
//        test = patient.searchReservationInfo(sample[position])!!
        test = Patient.searchReservationInfo(sample[position])!!

        Log.e("환자이름", test["patient_name"].toString())
        doc_name.text = test["doctor_name"].toString()
        var contime = test["diagnosis_time"].toString()
        if(contime.toInt() == 0)
        {
            contime = "10:00"
        }
        else if(contime.toInt() == 1)
        {
            contime = "11:00"
        }
        else if(contime.toInt() == 2)
        {
            contime = "15:00"
        }
        else if(contime.toInt() == 3)
        {
            contime = "16:00"
        }
        time.text = contime
        date.text = test["diagnosis_date"].toString()
        val room_open : Int
        if(test["room_open"].toString() == "0")
        {
            btn.text = "닫힘"
            btn.setEnabled(false)
        }
        else if(test["room_open"].toString() == "1")
        {
            btn.text = "열림"
        }
        btn.setOnClickListener {
            ReservationNum = sample[position]
        }

        return view
    }

    init {
        mContext = context
        sample = reservations
        mLayoutInflater = LayoutInflater.from(mContext)
    }
}