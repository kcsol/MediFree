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
import com.knu.medifree.adapter.HospitalAdapter
import com.knu.medifree.adapter.MajorAdapter
import com.knu.medifree.functions.Patient
import java.util.ArrayList

//        import com.example.promise_lab.R;
//        import com.example.promise_lab.lib.MyToast;
class PSelhospActivity : Activity() {
    private var list_majors = ArrayList<String>()
    private lateinit var uid: String
    private lateinit var mAuth: FirebaseAuth
    private lateinit var hospital_name: String
    private lateinit var major_name: String
    lateinit var user_data : TextView
    lateinit var tv : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_sel_hos)


        var intent = getIntent()
        val user_name = intent.getStringExtra("name")
        val major = intent.getIntExtra("major",-1)
        user_data = findViewById<TextView>(R.id.p_hos_data)
        user_data.text = user_name +">" + Patient.convertMajor(major)
//        var intent = getIntent()
//        Toast.makeText(this, intent.getStringExtra("major"), Toast.LENGTH_SHORT).show()
        /* PSelmajorActivity 설명
     *       - activity_p_sel_doc.xml 참조.
     *       - PSelhospaActivity에서 hospital name을 선택해서 희성이가 구현한 startmajor~~~로 major Arraylist를 가져왔음.
     *       - PSelMajorActivity는 activity_p_sel_doc.xml layout을 사용
     *       목적 : 넘겨받은 major를 listview로 구현해서 "선택"하면 major id를 PSeldocActivity로 넘김.
     */
//            val textView = findViewById<View>(R.id.p_sel_doc_name) as TextView
//            textView.text = "$hospital_name is selected"

        //Debug
//            Log.e("hos name :", hospital_name!!)

        // 현재 uid 가져오기.
//        mAuth = FirebaseAuth.getInstance()
//        major_name = intent.getStringExtra("major")!!



        // Major_list
        //16:48 Major_list 어떻게 할건지 모르겠음 일단 넘어감.
//            val hospital_list: ArrayList<Hospital> = DBManager.getHospitals()
//            for (i in hospital_list.indices) {
//                if (hospital_list[i].getHospitalName().equals(hospital_name)) {
//                    list_majors = hospital_list[i].getMajors()
//                    Log.d("TAG", "onCreate:  list_majors" + list_majors.size)
//                    break
//                }
//            }
        var tmp = ArrayList<String>()
        tmp = Patient.searchHospital(major) as ArrayList<String>

        // dunp majors 만들어서 사용하는 부분임.

//        tmp.add("123");
//        tmp.add("123");tmp.add("123");tmp.add("123");tmp.add("123");tmp.add("123");
        // tmp부분 지우고 넣으면 됨
        val adapter = HospitalAdapter(this, tmp) //tmp에 list_majors넣으면 됨

        // Attach the adapter to a ListView
        // Attach the adapter to a ListView
        val listView = findViewById<ListView>(R.id.listview_hospital)
        listView.adapter = adapter
        listView.onItemClickListener =
            OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                hospital_name = adapter.getItem(position).toString()
//                Log.d("TAG", "onCreate: majorname $major_name")

                var intent2 = Intent(applicationContext, PSeldocActivity::class.java)
                intent2.putExtra("name", user_name)
                intent2.putExtra("major", major)
                intent2.putExtra("hospital", hospital_name)
                startActivity(intent2)
            }
    }
}