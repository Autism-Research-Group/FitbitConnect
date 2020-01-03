package com.example.fitbitconnect.models

class DayHeartRate( val dateTime: String, val heartRateList: ArrayList<HeartRate>)

class HeartRate(val min: Int, val mas: Int, val name: String)

class UserHeartRate(val username: String, val userID: String, heartRateList: ArrayList<DayHeartRate>)
