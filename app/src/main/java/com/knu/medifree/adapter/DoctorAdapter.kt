package com.knu.medifree.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.knu.medifree.R


class DoctorAdapter(context: Context?, data: ArrayList<String>) :
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

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        val view: View = mLayoutInflater!!.inflate(R.layout.doc_item, null)
//        val imageView = view.findViewById<ImageView>(R.id.listview_doctor_item_pic)
//        val major = view.findViewById<TextView>(R.id.listview_doctor_item_major)
        val doctor = view.findViewById<TextView>(R.id.listview_doctor_item_name)
        doctor.text = sample[position]
        //사진 이슈 있음
        //name.setText(sample[position].getName())
        //major.setText(sample[position].getMajor())
        return view
    }

    init {
        mContext = context
        sample = data
        mLayoutInflater = LayoutInflater.from(mContext)
    }
}