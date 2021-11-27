package com.knu.medifree.adapter


import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.knu.medifree.R
import com.knu.medifree.functions.Patient
import java.util.ArrayList


class ConfAdapter(context: Context?, reservations: ArrayList<String>) :
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
        val view: View = mLayoutInflater!!.inflate(R.layout.conf_item, null)
        val date = view.findViewById<TextView>(R.id.office_p_date)
        val time = view.findViewById<TextView>(R.id.office_p_time)
        val type = view.findViewById<TextView>(R.id.office_p_diag_type)

        //DB에서 각 reservation에 대한 환자이름, 시간 load
        val test : Map<String, Any?>
//        val patient = Patient("qzngwoSZNafRWnnH4QaCy9nz3ft1") // -> reservation으로 변경해야함
//        Log.e("laod", "DBLoad")
//        test = patient.searchReservationInfo(sample[position])!!
        test = Patient.searchReservationInfo(sample[position])!!

        Log.e("환자이름", test["patient_name"].toString())
        date.text = test["diagnosis_date"].toString()
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
        when(test["diagnosis_type"].toString()) {
            "1" -> type.setText("대면")
            "2" -> type.setText("비대면")
            else -> type.setText("미결정")
        }


        return view
    }

    init {
        mContext = context
        sample = reservations
        mLayoutInflater = LayoutInflater.from(mContext)
    }
}