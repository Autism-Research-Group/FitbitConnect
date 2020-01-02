package com.example.fitbitconnect.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitbitconnect.R
import kotlinx.android.synthetic.main.activity_new_user.*

class NewUserActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_user)

        btn_submit.setOnClickListener {
            val text = editText_userID.text

        }

    }
}