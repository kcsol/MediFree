package com.knu.medifree.adapter


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


class QuestionnaireAdapter(context: Context?, data: ArrayList<String>) :
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
        val view: View = mLayoutInflater!!.inflate(R.layout.questionnaire_item, null)
        val doctor = view.findViewById<TextView>(R.id.quest_p_doctor)
        val time = view.findViewById<TextView>(R.id.quest_p_time)
        val date = view.findViewById<TextView>(R.id.quest_p_date)
        val complete = view.findViewById<TextView>(R.id.is_complete)
        val test : Map<String, Any?>
//        val patient = Patient("qzngwoSZNafRWnnH4QaCy9nz3ft1") // -> reservation으로 변경해야함
//        Log.e("laod", "DBLoad")
//        test = patient.searchReservationInfo(sample[position])!!
        test = Patient.searchReservationInfo(sample[position])!!
        Log.e("환자이름", test["patient_name"].toString())
        doctor.text = test["doctor_name"].toString()
        date.text = test["diagnosis_date"].toString()
        var contime = test["diagnosis_time"].toString()
        val is_complete = test["is_completed"].toString()
        if(is_complete == "0")
        {
            complete.text = "의사가 문진표를 작성하지 않았습니다."
        }
        else if(is_complete == "1")
        {
            complete.text = "문진표 작성하러 가기"
        }
        else if(is_complete == "2")
        {
            complete.text = "의사가 확인하지 않았습니다."
        }
        else if(is_complete == "3")
        {
            complete.text = "의사가 확인을 완료했습니다."
        }
        else{
            complete.text = "왜안됨?"
        }
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
        return view
    }

    init {
        mContext = context
        sample = data
        mLayoutInflater = LayoutInflater.from(mContext)
    }
}