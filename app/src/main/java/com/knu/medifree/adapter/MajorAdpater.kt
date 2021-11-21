package com.knu.medifree.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.knu.medifree.R
import java.util.ArrayList


class MajorAdapter(context: Context?, data: ArrayList<String>) :
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
        val view: View = mLayoutInflater!!.inflate(R.layout.major_item, null)
        val major = view.findViewById<TextView>(R.id.major_name)
        major.text = sample[position]
        return view
    }

    init {
        mContext = context
        sample = data
        mLayoutInflater = LayoutInflater.from(mContext)
    }
}