package com.example.fitbitconnect.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitbitconnect.WebFetches.FetchAllUsers
import com.example.fitbitconnect.R

import java.net.URL

class UserListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_userlist)

        FetchAllUsers(this).execute(URL("http://10.0.2.2:3000/database/allUsers"))
    }
}