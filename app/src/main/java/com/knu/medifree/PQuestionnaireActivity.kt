package com.knu.medifree

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.knu.medifree.adapter.QuestionnaireAdapter
import com.knu.medifree.functions.Patient

class PQuestionnaireActivity : AppCompatActivity() {
    lateinit var list_quest : ArrayList<String>
    lateinit var question : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_questionaire)

        var tmp = ArrayList<String>()
        tmp = Patient.searchPatientReservationList() as ArrayList<String>


        val adapter = QuestionnaireAdapter(this, tmp) //tmp에 list_majors넣으면 됨
        val listView = findViewById<ListView>(R.id.listview_questionnaire)
        listView.adapter = adapter
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                question = adapter.getItem(position).toString()

                val test : Map<String, Any?>
                var is_completed : String
                var question_temp : String = question
                test = Patient.searchReservationInfo(question)!!
                Log.e("is_completed", test["is_completed"].toString())
                is_completed = test["is_completed"].toString()
                if(is_completed.toInt() == 0)
                {
                    Toast.makeText(this, "의사가 문진표를 작성하지 않았습니다.", Toast.LENGTH_LONG).show()
                }
                else if(is_completed.toInt() == 1)
                {
                    val intent2 = Intent(applicationContext, PQuestActivity::class.java)
                intent2.putExtra("res", question_temp)
                    startActivity(intent2)
                }
                else if(is_completed.toInt() == 2)
                {
                    Toast.makeText(this, "의사가 작성한 문진표를 확인하지 않았습니다.", Toast.LENGTH_LONG).show()
                }
                else if(is_completed.toInt() == 3)
                {
                    Toast.makeText(this, "대면/비대면 진료가 확정되었습니다.", Toast.LENGTH_LONG).show()
                }
//                Log.d("TAG", "onCreate: majorname $major_name")
            }
    }
}