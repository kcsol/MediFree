package com.knu.medifree

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
//import com.knu.medifree.util.DBManager


class PHomeActivity : AppCompatActivity() {
    lateinit var btn_app: ImageButton
    lateinit var btn_quest: ImageButton
    lateinit var btn_con: ImageButton
    lateinit var btn_treat: ImageButton
    //lateinit var intent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_home)
        var intent = getIntent()
        Log.i("HEESUNG_DHOME", "Welcome" + intent.getStringExtra("user_id"))

        // 객체 할당
        btn_app = findViewById<ImageButton>(R.id.p_appoint)
        btn_quest = findViewById<ImageButton>(R.id.p_quest)
        btn_con = findViewById<ImageButton>(R.id.p_con)
        btn_treat = findViewById<ImageButton>(R.id.p_treat)
        // 클릭 리스너 할당
        btn_app.setOnClickListener {
            // 예약하기 버튼을 눌렀을 때
            // 현재 상황 : PReserveActivity로 이동
            val intent = Intent(applicationContext, PReserveActivity::class.java)
            startActivity(intent)
            //finish(); 일단 뒤로 버튼을 눌러서 의사 홈으로 돌아올 수 있게 해둠.
        }
        btn_quest.setOnClickListener {
            // 문진표 버튼을 눌렀을 때
            // 현재 상황 : PCheckStatus로 이동
            val intent = Intent(applicationContext, PQuestionaire::class.java)
            //DBManager.startActivityWithReservationReading(this@DHomeActivity, intent)
            // TODO :
        }
        btn_con.setOnClickListener {
            // 예약확인 버튼을 눌렀을 때
            // 현재 상황 :PConfirm로 이동
            val intent = Intent(applicationContext, PConfirm::class.java)
            //DBManager.startActivityWithReservationReading(this@DHomeActivity, intent)
            // TODO :
        }
        btn_treat.setOnClickListener {
            // PTreat 버튼을 눌렀을 때
            // 현재 상황 :PTreat로 이동
            val intent = Intent(applicationContext, PTreat::class.java)
            //DBManager.startActivityWithReservationReading(this@DHomeActivity, intent)
            // TODO :
        }
    }
}