package com.knu.medifree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.knu.medifree.functions.Account

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val account = Account(this.applicationContext)
        var uid = account.signUp("test@abc.com", "password123!@#")
        Log.i("uid", uid.toString())

        var signIn = account.signIn("test@abc.com", "password123!@#")
        Log.i("signIn", signIn.toString())
    }
}