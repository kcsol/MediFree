package com.knu.medifree

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.knu.medifree.adapter.QuestionnaireAdapter

class PQuestionnaireActivity : AppCompatActivity() {
    lateinit var list_quest : ArrayList<String>
    lateinit var question : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_questionaire)

        val tmp = ArrayList<String>()
        tmp.add("문진표1")
        tmp.add("문진표2")
        tmp.add("문진표3")


        val adapter = QuestionnaireAdapter(this, tmp) //tmp에 list_majors넣으면 됨
        val listView = findViewById<ListView>(R.id.listview_questionnaire)
        listView.adapter = adapter
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                question = adapter.getItem(position).toString()
//                Log.d("TAG", "onCreate: majorname $major_name")
                val intent2 = Intent(applicationContext, PQuestActivity::class.java)
////                intent2.putExtra("major", major_name)
                startActivity(intent2)
            }
    }
}