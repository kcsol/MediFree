package com.knu.medifree.adapter


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.knu.medifree.R
import com.knu.medifree.functions.Doctor


class ReservationAdapter(context: Context?, reservations: List<String>) :
    BaseAdapter() {
    var mContext: Context? = null
    var mLayoutInflater: LayoutInflater? = null
    var sample: List<String>

    override fun getCount(): Int {
        return sample.size
    }

    override fun getItem(position: Int): Any {
        return sample[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var convertview = view
        val holder : ViewHolder

        if (view == null) {
            convertview = LayoutInflater.from(parent?.context)
                .inflate(R.layout.listview_reservation, parent, false)
            holder = ViewHolder()
            holder.name = convertview!!.findViewById<TextView>(R.id.office_untact_patient)
            holder.time = convertview.findViewById<TextView>(R.id.office_untact_time)
            holder.type = convertview.findViewById<TextView>(R.id.office_diag_type)
            convertview.tag = holder
        }

        else {
            holder = view?.tag as ViewHolder
            convertview = view
            return convertview
        }

        //DB에서 각 reservation에 대한 환자이름, 시간 load
        val reservation : Map<String, Any?>

        reservation = Doctor.searchReservationInfo(sample[position])!!

        Log.e("환자이름", reservation["patient_name"].toString())
        holder.name?.text = reservation["patient_name"].toString()
        when(reservation["diagnosis_time"].toString()) {
            "0" -> holder.time?.text = "10:00"
            "1" -> holder.time?.text = "11:00"
            "2" -> holder.time?.text = "15:00"
            "3" -> holder.time?.text = "16:00"
        }

        when(reservation["diagnosis_type"].toString()) {
            "1" -> holder.type?.setText("대면")
            "2" -> holder.type?.setText("비대면")
            else -> holder.type?.setText("미결정")
        }


        return convertview
    }

    private class ViewHolder {
        var name : TextView? = null
        var time : TextView? = null
        var type : TextView? = null
    }

    init {
        mContext = context
        sample = reservations
        mLayoutInflater = LayoutInflater.from(mContext)
    }
}