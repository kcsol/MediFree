package com.knu.medifree

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.knu.medifree.adapter.ReservationAdapter
import java.util.*

//import com.knu.medifree.util.DBManager

class DAppActivity : AppCompatActivity() {
    private lateinit var btn_check: Button
    private lateinit var btn_diag: Button
    private lateinit var btn_refresh: ImageButton
    private lateinit var timelist: TextView
    private lateinit var timelist1: TextView
    private lateinit var timelist2: TextView
    private lateinit var timelist3: TextView

    private lateinit var btn_date_select : Button
    private lateinit var reservation_list : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_appoint)

        // 객체 할당
//        btn_check = findViewById<Button>(R.id.d_home_btn_check)
        btn_diag = findViewById<Button>(R.id.d_home_btn_diag)
        btn_refresh = findViewById<ImageButton>(R.id.d_home_btn_refresh)
//        timelist = findViewById<TextView>(R.id.ten_oclock)
//        timelist1 = findViewById<TextView>(R.id.eleven_oclock)
//        timelist2 = findViewById<TextView>(R.id.fifthteen_oclock)
//        timelist3 = findViewById<TextView>(R.id.sixteen_oclock)

        // 클릭 리스너 할당
//        timelist!!.setOnClickListener(time)
//        timelist1!!.setOnClickListener(time)
//        timelist2!!.setOnClickListener(time)
//        timelist3!!.setOnClickListener(time)

//        btn_check.setOnClickListener {
//            // 예약하기 버튼을 눌렀을 때
//            // 현재 상황 : ResCheckActivity로 이동
//            val intent = Intent(applicationContext, DResCheckActivity::class.java)
//            startActivity(intent)
//            //DBManager.startActivityWithReservationReading(this@DAppActivity, intent)
//            //finish(); 일단 뒤로 버튼을 눌러서 의사 홈으로 돌아올 수 있게 해둠.
//        }

        btn_date_select = findViewById<Button>(R.id.d_appoint_btn_selectdate)
        reservation_list = findViewById<ListView>(R.id.d_appoint_list_reservations)

        btn_date_select.setOnClickListener {
            val today = GregorianCalendar()
            val year: Int = today.get(Calendar.YEAR)
            val month: Int = today.get(Calendar.MONTH)
            val date: Int = today.get(Calendar.DATE)
            val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    btn_date_select.setText("${year}/${month+1}/${dayOfMonth}")
                    Log.e("date", btn_date_select.text.toString())
                    val test = findres("test", "test")

                    var adapter : ReservationAdapter = ReservationAdapter(this@DAppActivity, test)
                    reservation_list = findViewById<ListView>(R.id.d_appoint_list_reservations)
                    reservation_list.adapter = adapter
                }
            }, year, month, date)
            dlg.show()

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

//    var time = View.OnClickListener { v ->
//        val intent = Intent(applicationContext, DetailAppActivity::class.java)
//        intent.putExtra("time", v.tag.toString())
//        startActivity(intent)
//        //DBManager.startActivityWithReservationReading(this@DAppActivity, intent)
//    }

    private fun findres (name : String, date : String) : ArrayList<String>  {
        //test용
        val reservations = arrayListOf("test1", "test2")
        return reservations
    }
}