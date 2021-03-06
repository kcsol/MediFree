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
import com.knu.medifree.functions.Patient
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


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
    lateinit var hospital : String
    lateinit var major : String
    lateinit var checkdate: String
    lateinit var currentdate: String
    lateinit var dialog: Dialog
    lateinit var user_data : TextView
    lateinit var currenttime : String
    var timedata : String = "2020-00-00"
    var docSchedule : List<Boolean> = listOf(true, true, true, true)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_sel_time)
        check1 = false
        check2 = false
        var intent = getIntent()
        val user_name = intent.getStringExtra("name")
        patient_name = user_name!!
        val major2 = intent.getIntExtra("major",-1)
        major = Patient.convertMajor(major2)!!
        val hos = intent.getStringExtra("hospital")
        hospital = hos!!
        val doc = intent.getStringExtra("doctor")
        doctor_name = doc!!
        user_data = findViewById<TextView>(R.id.p_time_data)
        user_data.text = patient_name +">" + Patient.convertMajor(major2) + ">" + hospital + ">" + doctor_name


        val group = findViewById<RadioGroup>(R.id.time_list)
        val time10 = findViewById<RadioButton>(R.id.ten_oclock)
        val time11 = findViewById<RadioButton>(R.id.eleven_oclock)
        val time15 = findViewById<RadioButton>(R.id.fifteen_oclock)
        val time16 = findViewById<RadioButton>(R.id.sixteen_oclock)
//        textView.text = "none"
//        Toast.makeText(this, intent.getStringExtra("doctor"), Toast.LENGTH_SHORT).show()
//            intent.getStringExtra("hospital_name") + " " + intent.getStringExtra("name") + " is selected"

        //intent??? ?????? ?????? ????????????
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
                patient_date.text = "$year-$monthOfYear-$dayOfMonth"
                timedata = patient_date.text.toString()
                docSchedule = Patient.searchDoctorSchedule(doctor_name, timedata!!)!!
        //        Toast.makeText(this,docSchedule[0].toString()+"/"+docSchedule[1].toString()+"/"+docSchedule[2].toString()+"/"+docSchedule[3].toString(), Toast.LENGTH_LONG).show()
                if(docSchedule!![0] == false && time10.isEnabled())
                {
                    time10.setEnabled(false)
                    var time_text :String = time10.text as String
                    time_text = time_text + " is Disable"
                    time10.text = time_text
                }
                else
                {
                    time10.setEnabled(true)
                    time10.text = "10:00"
                }
                if(docSchedule!![1] == false&& time11.isEnabled())
                {
                    time11.setEnabled(false)
                    var time_text :String = time11.text as String
                    time_text = time_text + " is Disable"
                    time11.text = time_text
                }
                else
                {
                    time11.setEnabled(true)
                    time11.text = "11:00"
                }
                if(docSchedule!![2] == false&& time15.isEnabled())
                {
                    time15.setEnabled(false)
                    var time_text :String = time15.text as String
                    time_text = time_text + " is Disable"
                    time15.text = time_text
                }
                else
                {
                    time15.setEnabled(true)
                    time15.text = "15:00"
                }
                if(docSchedule!![3] == false&& time16.isEnabled())
                {
                    time16.setEnabled(false)
                    var time_text :String = time16.text as String
                    time_text = time_text + " is Disable"
                    time16.text = time_text
                }
                else
                {
                    time16.setEnabled(true)
                    time16.text = "16:00"
                }
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
            docSchedule = Patient.searchDoctorSchedule(doctor_name, timedata!!)!!
//            if(docSchedule!![0] == true && time10.isEnabled())
//            {
//                time10.setEnabled(false)
//                var time_text :String = time10.text as String
//                time_text = time_text + " is Disable"
//                time10.text = time_text
//            }
//            else
//            {
//                time10.setEnabled(true)
//                time10.text = "10:00"
//            }
//            if(docSchedule!![1] == false&& time10.isEnabled())
//            {
//                time11.setEnabled(false)
//                var time_text :String = time10.text as String
//                time_text = time_text + " is Disable"
//                time11.text = time_text
//            }
//            else
//            {
//                time11.setEnabled(true)
//                time11.text = "11:00"
//            }
//            if(docSchedule!![2] == false&& time10.isEnabled())
//            {
//                time15.setEnabled(false)
//                var time_text :String = time15.text as String
//                time_text = time_text + " is Disable"
//                time15.text = time_text
//            }
//            else
//            {
//                time11.setEnabled(true)
//                time11.text = "11:00"
//            }
//            if(docSchedule!![3] == false&& time10.isEnabled())
//            {
//                time16.setEnabled(false)
//                var time_text :String = time16.text as String
//                time_text = time_text + " is Disable"
//                time16.text = time_text
//            }
//            else
//            {
//                time11.setEnabled(true)
//                time11.text = "11:00"
//            }
            val st = StringTokenizer(currentdate, "-")
//            Log.d("TAG", "onClick: $currentdate")
            val dialog = DatePickerDialog(
                this@PSeltimeActivity, callbackMethod, st.nextToken().toInt(),
                st.nextToken().toInt() - 1, st.nextToken().toInt()
            )
            dialog.show()
        }

        //????????? ?????? ??????


        time10.setOnClickListener(RadioClick)
        time11.setOnClickListener(RadioClick)
        time15.setOnClickListener(RadioClick)
        time16.setOnClickListener(RadioClick)
        group.setOnCheckedChangeListener(checkedChangeListener)
        //?????? ????????? ????????? ??????!


        //??????????????? ?????? ??????
        dialog = Dialog(this@PSeltimeActivity)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialog_check)


//        Spinner patient_time = (Spinner) findViewById(R.id.p_res_time);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
//                (String[]) getResources().getStringArray(R.array.spinner_time));
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        patient_time.setAdapter(arrayAdapter);
//        patient_time.setSelection(0);


        // ?????? ??????
        /* ?????? ??? Acitivity ??????
            1. ???????????? ????????????
            2. ??? ???????????? String?????? ????????????.
            3. Calender??? ???????????? ?????? ???,???,?????? ????????????.
            4. Firestore??? ????????? ?????? ????????? ?????????.
                ?????? ?????? String format => "2020/12/16/10/00" (???/???/???/???/???) ?????? ????????? ?????????.
            5. Reservation ?????? ???????????? ?????? ?????? -> model.Reservation ????????? ??????.
         */
        // ?????? uid ????????????.
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser
        cur_uid = user!!.uid

        // Reservation ?????? ?????? , date ??? ????????? ??????.
//        date = "2020/12/16/11/00";
//        Reservation res = new Reservation(cur_uid, doctor_id, date,false);
        p_sel_time_btn_diag = findViewById<Button>(R.id.p_sel_time_btn_diag)
        p_sel_time_btn_diag.setOnClickListener { // Go TypeActivity

            if(currenttime.toInt() == 10)
                currenttime = 0.toString()
            else if(currenttime.toInt() == 11)
                currenttime = 1.toString()
            else if(currenttime.toInt() == 15)
                currenttime = 2.toString()
            else if(currenttime.toInt() == 16)
                currenttime = 3.toString()

            Patient.addNewReservation(doctor_name,timedata, currenttime.toInt())

            startToast(" ??????????????? ?????????????????????. ????????? ???????????? ??????????????? ???????????????.")
            val intent2 = Intent(applicationContext, PHomeActivity::class.java)
            startActivity(intent2)
        }
    }

    fun showDialog() {
        dialog!!.show()
        val noBtn = dialog!!.findViewById<Button>(R.id.noBtn)
        noBtn.setOnClickListener { dialog!!.dismiss() }
        dialog!!.findViewById<View>(R.id.yesBtn).setOnClickListener {
//            val res = Reservation(cur_uid, doctor_id, date, false, patient_name, doctor_name)
//            Log.d("TAG", "onClick: $res")
//            // ?????? ????????? ??????
//            DBManager.createReservation(res)
//            //PHomeActivity???????????? ?????? ????????????
            startToast(" ??????????????? ?????????????????????. ????????? ???????????? ??????????????? ???????????????.")
            val intent3 = Intent(applicationContext, PHomeActivity::class.java)
            startActivity(intent3)
            finish()
        }
    }

    var RadioClick = View.OnClickListener { v ->
        time = v.tag.toString().substring(0,2)
        currenttime = time as String
        startToast(currenttime + " selected")
    }
    var checkedChangeListener =
        RadioGroup.OnCheckedChangeListener { group, checkedId ->
            Log.d(
                "TAG",
                "onCheckedChanged: $checkedId"
            )
        }

    // ?????? ?????? ?????? ????????? ????????? ????????? ??????????????? ?????? ??? ?????????...
    override fun onClick(v: View) {
        //?????????????????????!!!
        /* ?????? time dialog??? ??????????????? ?????? ???????????????*/
        /* ?????? ????????? PHomeActivity??? ???????????? ??????????????? ?????? */
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
        if (date1!!.before(date2) || date1 == date2) startToast("???????????? ?????? ????????? ????????? ?????????.") else if (!check1 || !check2) {
            startToast("????????? ????????? ?????????.")
        } else {
            (dialog!!.findViewById<View>(R.id.timecheck) as TextView).text = "$date  ?????? ???????????????????"
            showDialog()
            // Reservation ?????? ?????? , date ??? ????????? ??????.
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