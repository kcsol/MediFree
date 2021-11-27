package com.knu.medifree

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.knu.medifree.functions.Doctor

class DQuestConfActivity : AppCompatActivity() {

    private lateinit var btn_contact : Button
    private lateinit var btn_untact : Button
    private lateinit var tv_quest_1 : TextView
    private lateinit var tv_quest_2 : TextView
    private lateinit var tv_quest_3 : TextView
    private lateinit var tv_quest_4 : TextView
    private lateinit var tv_answer_1 : TextView
    private lateinit var tv_answer_2 : TextView
    private lateinit var tv_answer_3 : TextView
    private lateinit var tv_answer_4 : TextView
    private lateinit var resnum : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_quest_conf)

        var intent = getIntent()
        resnum = intent.getStringExtra("resnum").toString()
        Log.e("resnum", resnum)

        btn_contact= findViewById<Button>(R.id.d_conf_btn_contact)
        btn_untact= findViewById<Button>(R.id.d_conf_btn_untact)

        tv_quest_1 = findViewById<EditText>(R.id.d_conf_quest_item1)
        tv_quest_2 = findViewById<EditText>(R.id.d_conf_quest_item2)
        tv_quest_3 = findViewById<EditText>(R.id.d_conf_quest_item3)
        tv_quest_4 = findViewById<EditText>(R.id.d_conf_quest_item4)
        tv_answer_1 = findViewById<EditText>(R.id.d_conf_answer_item1)
        tv_answer_2 = findViewById<EditText>(R.id.d_conf_answer_item2)
        tv_answer_3 = findViewById<EditText>(R.id.d_conf_answer_item3)
        tv_answer_4 = findViewById<EditText>(R.id.d_conf_answer_item4)


        val reservation = Doctor.searchReservationInfo(resnum)
        val questions : List<String> = reservation!!["질문"] as List<String>
        val answers : List<String> = reservation!!["답변"] as List<String>
        tv_quest_1.text = questions[0]
        tv_quest_2.text = questions[1]
        tv_quest_3.text = questions[2]
        tv_quest_4.text = questions[3]

        tv_answer_1.text = answers[0]
        tv_answer_2.text = answers[1]
        tv_answer_3.text = answers[2]
        tv_answer_4.text = answers[3]

        btn_contact.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("대면 진료로 결정하시겠습니까?")
                .setPositiveButton("예") { dialog, _ ->
                    dialog.dismiss()
                    Toast.makeText(this, "예", Toast.LENGTH_SHORT).show()
                    /*edit Text 내용을 db에 업로드 하는 함수*/
                    Doctor.addDiagnosisType(resnum, 1)
                    val intent = Intent(applicationContext, DHomeActivity::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("아니오") { dialog, _ ->
                    dialog.dismiss()
                    Toast.makeText(this, "아니오", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        btn_untact.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("비대면 진료로 결정하시겠습니까?")
                .setPositiveButton("예") { dialog, _ ->
                    dialog.dismiss()
                    Toast.makeText(this, "예", Toast.LENGTH_SHORT).show()
                    /*edit Text 내용을 db에 업로드 하는 함수*/
                    Doctor.addDiagnosisType(resnum, 2)
                    val intent = Intent(applicationContext, DHomeActivity::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("아니오") { dialog, _ ->
                    dialog.dismiss()
                    Toast.makeText(this, "아니오", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

    }
}