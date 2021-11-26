package com.knu.medifree

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class PSeltimeActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var p_sel_time_btn_diag: Button
    lateinit var mContext: Context
    //private val Dialog: CustomDialog? = null
    lateinit var mAuth: FirebaseAuth
    lateinit var doctor_id: String
    lateinit var cur_uid: String
    lateinit var date: String
    lateinit var doctor_name: String
    lateinit var patient_name: String
    lateinit var checkdate: String
    lateinit var currentdate: String
    lateinit var dialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_sel_time)
        check1 = false
        check2 = false
        val intent = getIntent()
        val textView = findViewById<TextView>(R.id.p_time_data)
//        textView.text = "none"
//        Toast.makeText(this, intent.getStringExtra("doctor"), Toast.LENGTH_SHORT).show()
//            intent.getStringExtra("hospital_name") + " " + intent.getStringExtra("name") + " is selected"

        //intent를 통해 정보 받아오기
//        doctor_name = intent.getStringExtra("doctor")!!

//        patient_name = DBManager.getPatientName()

        // Debug
//        Log.e("D_name : ", doctor_name!!)
//        Log.d("TAG", "onCreate: boolean " + check1 + check2)
        val patient_date = findViewById<TextView>(R.id.p_res_date2)
        val callbackMethod =
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                var monthOfYear = monthOfYear
                monthOfYear += 1
                patient_date.text = "$year/$monthOfYear/$dayOfMonth"
                Companion.year = year
                Companion.monthOfYear = monthOfYear
                Companion.dayOfMonth = dayOfMonth
                check1 = true
                date = null.toString()
            }
        patient_date.setOnClickListener {
            val currentTime = Calendar.getInstance().time
            currentdate =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(currentTime)
            val st = StringTokenizer(currentdate, "-")
//            Log.d("TAG", "onClick: $currentdate")
            val dialog = DatePickerDialog(
                this@PSeltimeActivity, callbackMethod, st.nextToken().toInt(),
                st.nextToken().toInt() - 1, st.nextToken().toInt()
            )
            dialog.show()
        }

        //라디오 그룹 부분
        val group = findViewById<RadioGroup>(R.id.time_list)
        val time10 = findViewById<RadioButton>(R.id.ten_oclock)
        val time11 = findViewById<RadioButton>(R.id.eleven_oclock)
        val time15 = findViewById<RadioButton>(R.id.fifteen_oclock)
        val time16 = findViewById<RadioButton>(R.id.sixteen_oclock)
        time10.setOnClickListener(RadioClick)
        time11.setOnClickListener(RadioClick)
        time15.setOnClickListener(RadioClick)
        time16.setOnClickListener(RadioClick)
        group.setOnCheckedChangeListener(checkedChangeListener)
        //시간 골라서 보내는 거임!


        //다이얼로그 코드 부분
        dialog = Dialog(this@PSeltimeActivity)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialog_check)


//        Spinner patient_time = (Spinner) findViewById(R.id.p_res_time);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
//                (String[]) getResources().getStringArray(R.array.spinner_time));
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        patient_time.setAdapter(arrayAdapter);
//        patient_time.setSelection(0);


        // 객체 할당
        /* 일단 이 Acitivity 흐름
            1. 시간대를 클릭한다
            2. 그 시간대를 String으로 받아온다.
            3. Calender를 사용하여 현재 년,월,일을 받아온다.
            4. Firestore에 들어갈 날짜 포맷을 만든다.
                예약 날짜 String format => "2020/12/16/10/00" (년/월/일/시/분) 으로 포맷을 만든다.
            5. Reservation 객체 생성자를 통해 생성 -> model.Reservation 클래스 참조.
         */
        // 현재 uid 가져오기.
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser
        cur_uid = user!!.uid

        // Reservation 객체 생성 , date 는 임시로 넣음.
//        date = "2020/12/16/11/00";
//        Reservation res = new Reservation(cur_uid, doctor_id, date,false);
        p_sel_time_btn_diag = findViewById<Button>(R.id.p_sel_time_btn_diag)
        p_sel_time_btn_diag.setOnClickListener { // Go TypeActivity
            val intent = Intent(applicationContext, PHomeActivity::class.java)
            startActivity(intent)
        }
    }

    fun showDialog() {
        dialog!!.show()
        val noBtn = dialog!!.findViewById<Button>(R.id.noBtn)
        noBtn.setOnClickListener { dialog!!.dismiss() }
        dialog!!.findViewById<View>(R.id.yesBtn).setOnClickListener {
//            val res = Reservation(cur_uid, doctor_id, date, false, patient_name, doctor_name)
//            Log.d("TAG", "onClick: $res")
//            // 예약 메소드 실행
//            DBManager.createReservation(res)
//            //PHomeActivity로가면서 요청 기다리기
            startToast(" 예약요청을 성공하였습니다. 요청이 완료되면 예약목록에 추가됩니다.")
            val intent2 = Intent(applicationContext, PHomeActivity::class.java)
            startActivity(intent2)
            finish()
        }
    }

    var RadioClick = View.OnClickListener { v ->
        time = v.tag.toString()
        startToast(time + " selected")
//        time = v.tag.toString()
//        check2 = true
//        Log.d("TAG", "onClick: " + time)
    }
    var checkedChangeListener =
        RadioGroup.OnCheckedChangeListener { group, checkedId ->
            Log.d(
                "TAG",
                "onCheckedChanged: $checkedId"
            )
        }

    // 예약 주고 받는 부분은 어디서 하는지 모르겠어서 일단 킵 합니당...
    override fun onClick(v: View) {
        //예외처리해야함!!!
        /* 밑에 time dialog가 먼지몰라서 일단 주석해놓음*/
        /* 예약 누르면 PHomeActivity로 옮기면서 예약메소드 실행 */
        date =
            year.toString() + "/" + monthOfYear.toString() + "/" + dayOfMonth.toString() + "/" + time
        checkdate =
            year.toString() + "-" + monthOfYear.toString() + "-" + dayOfMonth.toString() + "-"
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        var date1: Date? = null
        var date2: Date? = null
        try {
            date1 = sdf.parse(checkdate)
            date2 = sdf.parse(currentdate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (date1!!.before(date2) || date1 == date2) startToast("현재시간 보다 미래를 선택해 주세요.") else if (!check1 || !check2) {
            startToast("시간을 선택해 주세요.")
        } else {
            (dialog!!.findViewById<View>(R.id.timecheck) as TextView).text = "$date  예약 하시겠습니까?"
            showDialog()
            // Reservation 객체 생성 , date 는 임시로 넣음.
        }

//        switch (v.getId()) {
//            case R.id.p_sel_time_btn_diag:
//                Dialog = new CustomDialog(this);
//                WindowManager.LayoutParams params = this.Dialog.getWindow().getAttributes();
//
//                this.Dialog.getWindow().setAttributes(params);
//                Dialog.setCancelable(false);
//                Dialog.getWindow().setGravity(Gravity.BOTTOM);
//                Dialog.show();
//                break;
//        }
    }

    private fun startToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    companion object {
        var year = 0
        var monthOfYear = 0
        var dayOfMonth = 0
        private var check1 = false
        private var check2 = false
        private const val check3 = false
        private var time: String? = null
    }
}