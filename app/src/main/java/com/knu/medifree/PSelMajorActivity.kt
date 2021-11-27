package com.knu.medifree

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.knu.medifree.adapter.MajorAdapter
import com.knu.medifree.functions.Patient
import org.w3c.dom.Text
import java.util.ArrayList

class PSelmajorActivity : Activity() {
    private var list_majors = ArrayList<String>()
    private lateinit var uid: String
    private lateinit var mAuth: FirebaseAuth
    private lateinit var hospital_name: String
    private lateinit var major_name: String
    lateinit var use_name : TextView

    lateinit var btn_test : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_sel_major)

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
        mAuth = FirebaseAuth.getInstance()
        var intent = getIntent()
        val user_name = intent.getStringExtra("name")
        use_name = findViewById<TextView>(R.id.p_major_data)
        use_name.text = user_name

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
        val tmp = ArrayList<String>()
        tmp.add(Patient.convertMajor(0)!!)
        tmp.add(Patient.convertMajor(1)!!)
        tmp.add(Patient.convertMajor(2)!!)
        tmp.add(Patient.convertMajor(3)!!)
        // dunp majors 만들어서 사용하는 부분임.


//        tmp.add("123");
//        tmp.add("123");tmp.add("123");tmp.add("123");tmp.add("123");tmp.add("123");
        // tmp부분 지우고 넣으면 됨
        val adapter = MajorAdapter(this, tmp) //tmp에 list_majors넣으면 됨

        // Attach the adapter to a ListView
        // Attach the adapter to a ListView
        val listView = findViewById<ListView>(R.id.listview_major)
        listView.adapter = adapter
        listView.onItemClickListener =
            OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
                major_name = adapter.getItem(position).toString()
//                Log.d("TAG", "onCreate: majorname $major_name")
                var major_type : Int = -1
                if(major_name == "내과")
                {
                    major_type = Patient.MAJOR_INNER
                }
                else if(major_name == "이비인후과")
                {
                    major_type = Patient.MAJOR_EARNOSE
                }
                else if(major_name == "정신과")
                {
                    major_type = Patient.MAJOR_MENTAL
                }
                else if(major_name == "피부과")
                {
                    major_type = Patient.MAJOR_SKIN
                }
                val intent2 = Intent(applicationContext, PSelhospActivity::class.java)
                intent2.putExtra("name", user_name)
                intent2.putExtra("major", major_type)
                startActivity(intent2)
            }

        /*
         *   PSelhospAcitivity와 같이 여기서 부터 Listview를 구성
         *       -Major을 클릭하면, 그 major이름을 통해서 DBManager에 구현해놓은 startwithDoctor메소드로 Major관련 Doctor 가져옴.
         *       - Doctor로 가기위해서는 hospital name과 major name이 필요.
         *       - 선택된 major을 major_name변수에 집어넣음.
         *       - 현재는 서울대병원, 피부과 가정.
         *       - hospital name은 intent를 통해 넘어옴.
         */


    }
}