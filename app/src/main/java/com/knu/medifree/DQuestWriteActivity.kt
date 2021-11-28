package com.knu.medifree

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.knu.medifree.functions.Doctor
import com.knu.medifree.functions.Doctor.Companion.doctor

class DQuestWriteActivity : AppCompatActivity() {

    private lateinit var btn_submit : Button
    private lateinit var et_quest_1 : EditText
    private lateinit var et_quest_2 : EditText
    private lateinit var et_quest_3 : EditText
    private lateinit var et_quest_4 : EditText
    private lateinit var resnum : String
    private lateinit var questions : MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_quest_write)

        var intent = getIntent()
        resnum = intent.getStringExtra("resnum").toString()
        Log.e("resnum", resnum)

        btn_submit = findViewById<Button>(R.id.d_quest_write_submit_btn)
        et_quest_1 = findViewById<EditText>(R.id.d_et_quest_item1)
        et_quest_2 = findViewById<EditText>(R.id.d_et_quest_item2)
        et_quest_3 = findViewById<EditText>(R.id.d_et_quest_item3)
        et_quest_4 = findViewById<EditText>(R.id.d_et_quest_item4)


        btn_submit.setOnClickListener {
            questions = mutableListOf(et_quest_1.text.toString(), et_quest_2.text.toString(),
                et_quest_3.text.toString(), et_quest_4.text.toString())

            Log.e("questions", questions.toString())

            AlertDialog.Builder(this)
                .setTitle("제출 하겠습니까?")
                .setMessage("제출 하시겠습니까?")
                .setPositiveButton("예") { dialog, _ ->
                    dialog.dismiss()
                    Toast.makeText(this, "문진표 작성이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    /*edit Text 내용을 db에 업로드 하는 함수*/
                    Doctor.addQuestion(resnum, doctor["name"].toString(), questions as List<String>)
                    val intent = Intent(applicationContext, DHomeActivity::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("아니오") { dialog, _ ->
                    dialog.dismiss()
                    //Toast.makeText(this, "아니오", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

    }
}