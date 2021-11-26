package com.knu.medifree

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.LauncherActivity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.knu.medifree.adapter.Quest
import com.knu.medifree.adapter.QuestAdapter
import com.knu.medifree.adapter.QuestionnaireAdapter

class PQuestActivity : AppCompatActivity() {
    lateinit var list_quest : ArrayList<Quest>
    lateinit var question : String
    lateinit var tv_quest1 : TextView
    lateinit var et_quest1 : EditText
    lateinit var tv_quest2 : TextView
    lateinit var et_quest2 : EditText
    lateinit var tv_quest3 : TextView
    lateinit var et_quest3 : EditText
    lateinit var tv_quest4 : TextView
    lateinit var et_quest4 : EditText
    lateinit var btn_submit : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_write_quest)

        tv_quest1 = findViewById<TextView>(R.id.tv_quest_item1)
        et_quest1 = findViewById<EditText>(R.id.et_quest_item1)
        tv_quest2=findViewById<TextView>(R.id.tv_quest_item2)
        et_quest2=findViewById<EditText>(R.id.et_quest_item2)
        tv_quest3=findViewById<TextView>(R.id.tv_quest_item3)
        et_quest3=findViewById<EditText>(R.id.et_quest_item3)
        tv_quest4=findViewById<TextView>(R.id.tv_quest_item4)
        et_quest4=findViewById<EditText>(R.id.et_quest_item4)
        btn_submit = findViewById<Button>(R.id.p_quest_submit_btn)

        tv_quest1.setText("질문1")
        tv_quest2.setText("질문2")
        tv_quest3.setText("질문3")
        tv_quest4.setText("질문4")
        var questArray : Array<String>
        /*questArray에 문진표 받아오기
        *
        * for(i in 0 받아온 갯수)
        * {
        *   questArray[i] = 내용들
        *
        * }
        *
        *
        * */

        btn_submit.setOnClickListener { // Go TypeActivity
                    AlertDialog.Builder(this)
                .setTitle("제출 하겠습니까?")
                .setMessage("제출 하시겠습니까?")
                .setPositiveButton("예") { dialog, _ ->
                    dialog.dismiss()
                    Toast.makeText(this, "예", Toast.LENGTH_SHORT).show()
                    /*edit Text 내용을 db에 업로드 하는 함수*/
                    val intent = Intent(applicationContext, PHomeActivity::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("아니오") { dialog, _ ->
                    dialog.dismiss()
                    Toast.makeText(this, "아니오", Toast.LENGTH_SHORT).show()
                }
                .show()
        }


//        val tmp = ArrayList<Quest>()
//        tmp.add(Quest("문진표1"))
//        tmp.add(Quest("문진표2"))
//        tmp.add(Quest("문진표3"))
//
//
//        val listView = findViewById<ListView>(R.id.listview_question)
//        val adapter = QuestAdapter(this, tmp) //tmp에 list_majors넣으면 됨
//        listView.adapter = adapter
//
//        listView.onItemClickListener =
//            AdapterView.OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
//                question = adapter.getItem(position).toString()
//                if(adapter.getET(position) is EditText)
//                {
//                    adapter.getET(position)?.requestFocus()
//                    Toast.makeText(this, "마즘!", Toast.LENGTH_SHORT).show()
//                }
//                else
//                {
//                    val str : String = adapter.getET(position)?.javaClass?.name.toString()
//                    Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
//                }
//
//            }
    }
//    private inner class MyAdapter internal constructor() : BaseAdapter() {
//        private var layoutInflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        internal var myItems = java.util.ArrayList<LauncherActivity.ListItem>()
//        lateinit var context: Context
//        init {
//            for (i in 0..6) {
//                val listItem = LauncherActivity.ListItem()
//                listItem.className = "Caption$i"
//                myItems.add(listItem)
//            }
//            notifyDataSetChanged()
//        }
//        override fun getItem(position: Int): Any {
//            return position
//        }
//        override fun getItemId(position: Int): Long {
//            return position.toLong()
//        }
//        override fun getCount(): Int {
//            return myItems.size
//        }
//        @SuppressLint("InflateParams")
//        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
//            var convertView: View? = convertView
//            val holder: ViewHolder
//            if (convertView == null) {
//                holder = ViewHolder()
//                convertView = layoutInflater.inflate(R.layout.quest_item, null)
//                holder.caption = convertView.findViewById(R.id.et_question_item)
//                convertView.setTag(holder)
//            } else {
//                holder = convertView.tag as ViewHolder
//            }
//            holder.caption.setText(myItems.get(position).className)
//            holder.caption.id = position
//            holder.caption.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
//                if (!hasFocus) {
//                    val position = v.id
//                    val caption = v as EditText
//                    myItems[position].className = caption.text.toString()
//                }
//            }
//            return convertView
//        }
//    }
//    internal inner class ViewHolder {
//        lateinit var caption: EditText
//    }
//}
}