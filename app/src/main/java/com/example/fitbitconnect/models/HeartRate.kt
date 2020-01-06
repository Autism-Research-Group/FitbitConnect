package com.example.fitbitconnect.models

import java.io.Serializable

data class DayHeartRate(val username: String, val userID: String, val dateTime: String,
                   val heartRateList: ArrayList<HeartRate>) : Serializable

class HeartRate(val min: Int, val max: Int, val name: String): Serializable

//class UserHeartRate(val username: String, val userID: String, val heartRateList: ArrayList<DayHeartRate>)
