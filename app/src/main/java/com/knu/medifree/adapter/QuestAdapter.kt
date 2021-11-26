package com.knu.medifree.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.knu.medifree.R
import java.util.ArrayList

data class Quest(var tv_quest: String, var et_quest: EditText?) {
    constructor(tv_quest: String) : this(tv_quest, null)
}

    class QuestAdapter(context: Context?, data: ArrayList<Quest>) : BaseAdapter() {
    var mContext: Context? = null
    var mLayoutInflater: LayoutInflater? = null
    var sample: ArrayList<Quest>
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
        var convertView : View? = convertView
        val holder : ViewHolder
        val view: View = mLayoutInflater!!.inflate(R.layout.quest_item, null)
        val tv = view.findViewById<TextView>(R.id.tv_question_item)
        tv.text = sample[position].tv_quest
        return view
    }

    fun focus(position : Int){
        sample[position].et_quest?.requestFocus()
    }

    init {
        mContext = context
        sample = data
        mLayoutInflater = LayoutInflater.from(mContext)
    }
}

internal class ViewHolder{
    lateinit var caption: EditText
}
