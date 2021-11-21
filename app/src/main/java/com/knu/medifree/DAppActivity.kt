package com.knu.medifree

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

//import com.knu.medifree.util.DBManager

class DAppActivity : AppCompatActivity() {
    private lateinit var btn_check: Button
    private lateinit var btn_diag: Button
    private lateinit var btn_refresh: ImageButton
    private lateinit var timelist: TextView
    private lateinit var timelist1: TextView
    private lateinit var timelist2: TextView
    private lateinit var timelist3: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_appoint)

        // 객체 할당
        btn_check = findViewById<Button>(R.id.d_home_btn_check)
        btn_diag = findViewById<Button>(R.id.d_home_btn_diag)
        btn_refresh = findViewById<ImageButton>(R.id.d_home_btn_refresh)
        timelist = findViewById<TextView>(R.id.ten_oclock)
        timelist1 = findViewById<TextView>(R.id.eleven_oclock)
        timelist2 = findViewById<TextView>(R.id.fifthteen_oclock)
        timelist3 = findViewById<TextView>(R.id.sixteen_oclock)

        // 클릭 리스너 할당
        timelist!!.setOnClickListener(time)
        timelist1!!.setOnClickListener(time)
        timelist2!!.setOnClickListener(time)
        timelist3!!.setOnClickListener(time)

        btn_check.setOnClickListener {
            // 예약하기 버튼을 눌렀을 때
            // 현재 상황 : ResCheckActivity로 이동
            val intent = Intent(applicationContext, ResCheckActivity::class.java)
            startActivity(intent)
            //DBManager.startActivityWithReservationReading(this@DAppActivity, intent)
            //finish(); 일단 뒤로 버튼을 눌러서 의사 홈으로 돌아올 수 있게 해둠.
        }
        btn_diag.setOnClickListener { // 진료실 버튼을 눌렀을 때
            // 현재 상황 :
            // TODO :
            val intent = Intent(applicationContext, DOfficeActivity::class.java)
            startActivity(intent)
        }
        btn_refresh!!.setOnClickListener {
            // 새로고침 버튼을 눌렀을 때
            // 현재 상황 :
            // TODO :
        }
    }

    var time = View.OnClickListener { v ->
        //val intent = Intent(applicationContext, DetailAppActivity::class.java)
        intent.putExtra("time", v.tag.toString())
        //DBManager.startActivityWithReservationReading(this@DAppActivity, intent)
    }
}