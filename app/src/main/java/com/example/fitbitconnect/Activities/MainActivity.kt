package com.example.fitbitconnect.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fitbitconnect.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // fetch all users currently registered
        btn_show_all_users.setOnClickListener {
            val intent: Intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
        }

        // fetch all users data
        btn_fetch_all_heartRate.setOnClickListener {
            val intent = Intent(this, AllHeartRateActivity::class.java)
            startActivity(intent)
        }

        // Add a user the the database
         btn_addUser.setOnClickListener {
             val intent = Intent(this, UserWebViewActivity::class.java)
             startActivity(intent)
         }
    }
}
