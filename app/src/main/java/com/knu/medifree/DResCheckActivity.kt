//package com.knu.medifree
//
//import android.os.Bundle
//import android.widget.Button
//import android.widget.ImageButton
//import android.widget.ListView
//import androidx.appcompat.app.AppCompatActivity
////import com.knu.medifree.adapter.PatientAdapter
//
////
////package com.knu.medifree
////import com.knu.medifree.model.Doctor
////import com.knu.medifree.model.DoctorAdapter
////import com.knu.medifree.model.Hospital
////import com.knu.medifree.model.HospitalAdapter
////import com.knu.medifree.model.Patient
////import com.knu.medifree.model.PatientAdapter
////import com.knu.medifree.model.Reservation
////import com.knu.medifree.util.DBManager
////import org.webrtc.ContextUtils.getApplicationContext
//
//class DResCheckActivity : AppCompatActivity() {
////    var first_btn: Button? = null
////    var origin_btn: Button? = null
////    var dhome_btn: ImageButton? = null
////    private var btn_true: Button? = null
////    private var btn_false: Button? = null
//    //private var list_reservations: ArrayList<Reservation>? = null
//    private lateinit var first_btn : Button
//    private lateinit var dhome_btn : ImageButton
//    private lateinit var btn_true : Button
//    private lateinit var btn_false : Button
//    private lateinit var list_reservations : ArrayList<String>
//    private lateinit var listView : ListView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_d_res_check)
//
//        dhome_btn = findViewById<ImageButton>(R.id.backtodhome)
//        dhome_btn.setOnClickListener{
//            onBackPressed()
//            finish()
//        }
//        //reservation 갖고오는 함수 필요
//        val test = arrayListOf("아이유", "수지", "박보영")
//        var adapter : PatientAdapter = PatientAdapter(this, test)
//        listView = findViewById<ListView>(R.id.listview_patientrequest)
//        listView.adapter = adapter
//
//    //    populatePatientsList()
////        first_btn = findViewById<Button>(R.id.d_req_first)
////        dhome_btn = findViewById<ImageButton>(R.id.backtodhome)
////        btn_true = findViewById<Button>(R.id.btn_true)
////        btn_false = findViewById<Button>(R.id.btn_true)
//    //    list_reservations = DBManager.getReservations()
////        dhome_btn!!.setOnClickListener {
////            onBackPressed()
////            finish()
////        }
//
//
////        btn_true.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                String name = ( (TextView) findViewById(R.id.list_item_name) ).toString();
////                String time = ( (TextView) findViewById(R.id.listview_request_item_time) ).toString();
////
////                for (int i =0;i<list_reservations.size(); i++){
////                    if( (list_reservations.get(i).getPatient_name().equals(name) ) && (list_reservations.get(i).getDate().equals(time) )){
////                        Log.e("Update",list_reservations.get(i).getId());
////                        //DBManager.updateReservations( list_reservations.get(i).getId(), true);
////                        Intent intent = new Intent(getApplicationContext(), DHomeActivity.class);
////                        startActivity(intent);
////                        finish();
////                    }
////                }
////
////            }
////        });
////        btn_false.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                String name = ((TextView) findViewById(R.id.listview_request_item_name)).toString();
////                String time = ((TextView) findViewById(R.id.listview_request_item_time)).toString();
////
////                for (int i =0;i<list_reservations.size(); i++){
////                    if( (list_reservations.get(i).getPatient_name().equals(name) ) && (list_reservations.get(i).getDate().equals(time) )){
////                        Log.e("Delete",list_reservations.get(i).getId());
////                        //DBManager.deleteReservations( list_reservations.get(i).getId());
////                        Intent intent = new Intent(getApplicationContext(), ResCheckActivity.class);
////                        DBManager.startActivityWithReservationReading(ResCheckActivity.this,intent);
////                    }
////                }
////
////            }
////        });
//    }
//
////    private fun populatePatientsList() {
////        val arrayOfUsers: ArrayList<*> = Patient.getPatient()
////        // Create the adapter to convert the array to views
////        val adapter = PatientAdapter(this, arrayOfUsers)
////        // Attach the adapter to a ListView
////        val listView = findViewById<View>(R.id.listview_patientrequest) as ListView
////        listView.adapter = adapter
////    }
//}