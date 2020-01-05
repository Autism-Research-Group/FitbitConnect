package com.example.fitbitconnect.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitbitconnect.models.DayHeartRate
import com.example.fitbitconnect.R
import kotlinx.android.synthetic.main.activity_detailed_heartrate.*

class DetailedHeartRateActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detailed_heartrate)

        // Get the data from the intent that was passed in
        val userData = intent.extras!!.get("User") as DayHeartRate

        //Display the data
        user_id.text = userData.userID
        username.text = userData.username
        date.text = userData.dateTime

        // Out of range
        ofr_type.text = userData.heartRateList[0].name
        ofr_max.text = "Max: ${userData.heartRateList[0].max}"
        ofr_min.text = "Min: ${userData.heartRateList[0].min}"

        // Fat Burn
        fat_type.text = userData.heartRateList[1].name
        fat_max.text = "Max: ${userData.heartRateList[1].max}"
        fat_min.text = "Min: ${userData.heartRateList[2].min}"

        // Cardio
        cardio_type.text = userData.heartRateList[2].name
        cardio_max.text = "Max: ${userData.heartRateList[2].max}"
        cardio_min.text = "Min: ${userData.heartRateList[2].min}"

        // Peak
        peak_type.text = userData.heartRateList[3].name
        peak_max.text = "Max: ${userData.heartRateList[3].max}"
        peak_min.text = "Min: ${userData.heartRateList[3].min}"
    }
}