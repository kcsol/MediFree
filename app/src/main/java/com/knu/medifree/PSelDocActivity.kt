package com.knu.medifree

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.knu.medifree.adapter.DoctorAdapter
import com.knu.medifree.adapter.HospitalAdapter
import kotlin.String


class PSeldocActivity : Activity() {
    //    public LinearLayout doctor1;
    //    private Context mContext;
    //    private CustomDialogTwo Dialog;
    //    public Button selectdoctor;
    lateinit var Doctor_name: String
    lateinit var Doctor_id: String
    lateinit var Hospital : String
    lateinit var mAuth: FirebaseAuth
    lateinit var Major : String
    lateinit var list_doctors: ArrayList<String>
    lateinit var user_data : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_sel_doc)

//        var intent = getIntent()
//        Toast.makeText(this, intent.getStringExtra("hospital"), Toast.LENGTH_SHORT).show()
        /*
           layout : activity_p_sel_doc_again.xml과 연결되어있음.
           목적 :  major을 통해서 해당 전공의를 ListView로 구현하여 전공의를 선택하는 부분을 구현
                   -> 그 후 PSeltimeActivity로 넘어감.
         */
//        mAuth = FirebaseAuth.getInstance()
//        val user = mAuth!!.currentUser
//        val cur_uid = user!!.uid
        // doctor를 Array list로 받아옴.
        //list_doctors = DBManager.getDoctors()
        //for debug
//        for (i in list_doctors.indices) {
//            Log.e("Doctor list :", list_doctors[i].getName()) // method, model.Doctor 참조.
//        }

        var intent = getIntent()
        val user_name = intent.getStringExtra("name")
        val major = intent.getStringExtra("major")
        val hos = intent.getStringExtra("hospital")
        user_data = findViewById<TextView>(R.id.p_sel_doc_data)
        user_data.text = user_name +" " + major + " " + hos

        val tmp = java.util.ArrayList<String>()
        tmp.add("의사1")
        tmp.add("의사2")
        tmp.add("의사3")
        tmp.add("의사4")
        tmp.add("의사5")

//        var intent = getIntent()
//        Major = intent.getStringExtra("major")!!
//        Hospital = intent.getStringExtra("hospital")!!

//        val doctorAdapter = DoctorAdapter(this, tmp)
//        val listView2 = findViewById<ListView>(R.id.listview_doctor)
//        listView2.adapter = doctorAdapter
//        listView2.onItemClickListener =
//            OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
//                Doctor_name = doctorAdapter.getItem(position).toString()
//                //Doctor_id = (doctorAdapter.getItem(position) as Doctor).getDocid()
////                val intent2 = Intent(applicationContext, PSeltimeActivity::class.java)
////                intent2.putExtra("hospital_name", Hospital)
////                intent2.putExtra("doctor", Doctor_name)
////                intent2.putExtra("major", Major)
////                Log.d(
////                    "TAG",
////                    "onCreate: doctorname $Doctor_name$Doctor_id" + intent.extras!!
////                        .getString("hospital_name")
////                )
////                DBManager.startActivityWithPatientReading(cur_uid, this@PSeldocActivity, intent2)
//                finish()
//            }
        val adapter = HospitalAdapter(this, tmp) //tmp에 list_majors넣으면 됨
        val listView = findViewById<ListView>(R.id.listview_doctor)
        listView.adapter = adapter
        listView.onItemClickListener =
            OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                 Doctor_name = adapter.getItem(position).toString()
//                Log.d("TAG", "onCreate: majorname $major_name")
                val intent2 = Intent(applicationContext, PSeltimeActivity::class.java)
                intent2.putExtra("name",user_name)
                intent2.putExtra("major", major)
                intent2.putExtra("hospital", hos)
                intent2.putExtra("doctor", Doctor_name)
                startActivity(intent2)
            }

        /*
         *           이밑으로 ListView 구현
         *               목표 : listview를 이번에는 클릭하면 이동하는게 아니라,
         *                       "선택"하고 "예약시간설정" 클릭하면 PSeltimeActivity로 이동함.
         *
         *               주의 : doctor_name을 intent로 넘겨야지 다음 날자정보&doctor정보로 예약 요청함.
         *
         *           현재 : default로 doctor_name = "양닥터"로 설정해놓음.
         *
         */

//        Doctor_name = "양닥터";    // get(i).getName()으로 받기가능
//        Doctor_id = "A9H8hbW4hAUa6FC781xjNsZYWmz2"; // get(i).getDocid()로 받기가능.
//
//        Button btn_time = (Button) findViewById(R.id.p_sel_doctor_again_btn_diag);
//
//        btn_time.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Intent intent2 = new Intent(getApplicationContext(), PSeltimeActivity.class);
//                intent2.putExtra("name",Doctor_name);
//                intent2.putExtra("id",Doctor_id);
//                startActivity(intent2);
//                finish();
//            }
//        });
    }
}