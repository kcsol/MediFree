package com.knu.medifree.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.knu.medifree.DHomeActivity
import com.knu.medifree.R

//import org.webrtc.ContextUtils.getApplicationContext

//Reservation 클래스 생성 후 그에 맞게 수정 필요
class PatientAdapter(context: Context?, users: ArrayList<String>) :
    ArrayAdapter<String>(context!!, 0, users!!) {
    private lateinit var list_reservations: ArrayList<String>
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        // Check if an existing view is being reused, otherwise inflate the view
        var convertView = convertView
        if (convertView == null) {
            convertView =
                LayoutInflater.from(context).inflate(R.layout.listview_request_item, parent, false)
        }

        // Get the data item for this position
        //val patient: Patient? = getItem(position)
        // Lookup view for data population
        //list_reservations = DBManager.getReservations()
        val listview_request_item_name =
            convertView!!.findViewById<TextView>(R.id.listview_request_item_name)
        val listview_request_item_time =
            convertView.findViewById<TextView>(R.id.listview_request_item_time)
        // Populate the data into the template view using the data object
        listview_request_item_name.setText(getItem(position))
        listview_request_item_time.setText("2021/11/25/15/00") // 임의값
        // Return the completed view to render on screen
        val btn_true = convertView.findViewById<View>(R.id.btn_true) as Button
        btn_true.setOnClickListener {
            Log.e("Update", position.toString())
            val intent = Intent(parent.context, DHomeActivity::class.java)
            parent.context.startActivity(intent)
//
//            for (i in list_reservations!!.indices) {
//                if (list_reservations!![i].getPatient_name()
//                        .equals(patient.getName()) && list_reservations!![i].getDate()
//                        .equals(patient.getTime())
//                ) {
//                    Log.e("Update", list_reservations!![i].getId())
//                    list_reservations!![i].aceept()
//                    DBManager.updateReservation(list_reservations!![i])
//                    val intent = Intent(parent.context, DHomeActivity::class.java)
//                    parent.context.startActivity(intent)
//                }
//            }
        }
        val btn_false = convertView.findViewById<View>(R.id.btn_false) as Button
        btn_false.setOnClickListener {
            Log.e("Delete", position.toString())
            val intent = Intent(parent.context, DHomeActivity::class.java)
            parent.context.startActivity(intent)
//            for (i in list_reservations!!.indices) {
//                if (list_reservations!![i].getPatient_name()
//                        .equals(patient.getName()) && list_reservations!![i].getDate()
//                        .equals(patient.getTime())
//                ) {
//                    Log.e("Delete", list_reservations!![i].getId())
//                    DBManager.deleteReservation(list_reservations!![i].getId())
//                    val intent = Intent(parent.context, DHomeActivity::class.java)
//                    parent.context.startActivity(intent)
//                }
//            }
        }
        return convertView
    }
}
