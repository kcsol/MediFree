package com.knu.medifree

import android.annotation.SuppressLint
import android.app.LauncherActivity
import android.content.Context
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_write_quest)



        val tmp = ArrayList<Quest>()
        tmp.add(Quest("문진표1"))
        tmp.add(Quest("문진표2"))
        tmp.add(Quest("문진표3"))


        val listView = findViewById<ListView>(R.id.listview_question)
        listView.itemsCanFocus = true
        val adapter = QuestAdapter(this, tmp) //tmp에 list_majors넣으면 됨


//        listView.onItemClickListener =
//            AdapterView.OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
//                question = adapter.getItem(position).toString()
//                Toast.makeText(this, "외않됨?", Toast.LENGTH_SHORT).show()
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