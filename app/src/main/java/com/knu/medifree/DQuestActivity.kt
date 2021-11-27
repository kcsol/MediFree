package com.knu.medifree

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.knu.medifree.adapter.DQuestAdapter
import com.knu.medifree.functions.Doctor

class DQuestActivity : AppCompatActivity() {
    private lateinit var btn_write : Button
    private lateinit var btn_conf : Button
    private lateinit var listview_write : ListView
    private lateinit var listview_conf : ListView
    private lateinit var reservations_write : List<String>
    private lateinit var reservations_conf : List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_quest)

        //객체 할당
        btn_write = findViewById<Button>(R.id.d_quest_btn_write)
        btn_conf = findViewById<Button>(R.id.d_quest_btn_conf)
        listview_write = findViewById<ListView>(R.id.d_quest_write_list)
        listview_conf = findViewById<ListView>(R.id.d_quest_conf_list)

        //작성, 확인해야 할 예약들 가져오기
        reservations_write = Doctor.searchDoctorToWrite()!!
        reservations_conf = Doctor.searchDoctorToConfirm()!!

        if (!reservations_write.isEmpty()) {
            var adapter_write: DQuestAdapter = DQuestAdapter(this, reservations_write)
            listview_write.adapter = adapter_write
            var reservation_write : String = ""

            listview_write.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
                reservation_write = adapter_write.getItem(position).toString()
                Log.e("res", reservation_write!!)
            }

            btn_write.setOnClickListener {
                if (reservation_write.equals("")) {
                    Toast.makeText(this, "작성할 문진표를 선택해주세요.", Toast.LENGTH_SHORT).show()
                }

                else {
                    intent = Intent(applicationContext, DQuestWriteActivity::class.java)
                    intent.putExtra("resnum", reservation_write)
                    startActivity(intent)
                }
            }

        }


        if (!reservations_conf.isEmpty()) {
            var adapter_conf: DQuestAdapter = DQuestAdapter(this, reservations_conf)
            listview_conf.adapter = adapter_conf
            var reservation_conf : String = ""

            listview_conf.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
                reservation_conf = adapter_conf.getItem(position).toString()
                Log.e("res", reservation_conf)
            }

            btn_conf.setOnClickListener {
                if (reservation_conf.equals("")) {
                    Toast.makeText(this, "확인할 문진표를 선택해주세요.", Toast.LENGTH_SHORT).show()
                }

                else {
                    intent = Intent(applicationContext, DQuestConfActivity::class.java)
                    intent.putExtra("resnum", reservation_conf)
                    startActivity(intent)
                }
            }
        }








        }
    }