package com.example.fitbitconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("URL_API", "In main")
        btn_api.setOnClickListener {
            Toast.makeText(this,"Making API call", Toast.LENGTH_SHORT).show()
            FetchURL().execute("http://10.0.2.2:3000/android/profile")
        }
    }


}
