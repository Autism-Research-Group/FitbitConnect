package com.example.fitbitconnect.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitbitconnect.R
import com.example.fitbitconnect.WebFetches.AddUser
import java.net.URL

class UserWebViewActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_user_webwindow)
        AddUser(this).execute(URL("http://10.0.2.2:3000/auth/authorize"))


    }
}