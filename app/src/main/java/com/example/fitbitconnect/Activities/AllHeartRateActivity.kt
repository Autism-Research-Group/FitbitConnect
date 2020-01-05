package com.example.fitbitconnect.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitbitconnect.WebFetches.FetchAllHeartRates
import com.example.fitbitconnect.R
import java.net.URL

class AllHeartRateActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heartrate_all)

        // Update the recycler view
        FetchAllHeartRates(this).execute(URL("http://10.0.2.2:3000/android/heartRate/period/all?range=1m"))
    }
}