package com.knu.medifree

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PQuestActivity : AppCompatActivity() {
    private lateinit var et_quest: EditText
    private lateinit var btn_submit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_questionaire)

        et_quest = findViewById<EditText>(R.id.quest_et_1)
        btn_submit = findViewById<Button>(R.id.quest_submit_btn)

        val quest = et_quest.text.toString()
        btn_submit.setOnClickListener{
            if(TextUtils.isEmpty(quest))
            {
                Toast.makeText(this, "문진표를 작성해 주세요.", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this, "문진표가 제출되었습니다..", Toast.LENGTH_SHORT).show()
        }
    }
}