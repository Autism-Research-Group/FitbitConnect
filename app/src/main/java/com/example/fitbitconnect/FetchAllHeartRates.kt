package com.example.fitbitconnect

import android.app.Activity
import com.example.fitbitconnect.models.DayHeartRate
import com.example.fitbitconnect.models.HeartRate
import com.example.fitbitconnect.models.UserHeartRate
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.ArrayList

class FetchAllHeartRates(context: Activity): FetchGetUrl<ArrayList<UserHeartRate>>(context) {

    override fun handleResponse(data: String): ArrayList<UserHeartRate> {
        val heartRateList: ArrayList<UserHeartRate> = ArrayList()

        val dataArray = JSONArray(data)

        // Add every element of the dataArray to the heartRateList
        for(i in 0 until dataArray.length()){
            val obj = dataArray.get(i) as JSONObject

            // Get the users information
            val userDataObject: JSONObject = obj.getJSONObject("user")
            val username: String = userDataObject.getString("username")
            val userID: String = userDataObject.getString("userID")

            // The the data for the user
            val user: JSONArray = obj.getJSONArray("activities-heart")

            // Create the data object for the user
            val dayList = createDayHeartRateList(user)

            // Create and add the user to the user list
            heartRateList.add(UserHeartRate(username, userID, dayList))
        }

        return heartRateList
    }


    // Creates the list of DayHeartRates using the given json array
    private fun createDayHeartRateList(array: JSONArray): ArrayList<DayHeartRate> {

        val dayHeartRateList = ArrayList<DayHeartRate>()
        for(i in 0 until array.length()){
            val userHeartRateObject: JSONObject = array.get(i) as JSONObject
            // The datetime
            val date = userHeartRateObject.getString("dateTime")
            // The array that will be passed to createHeartRateList() to create the list
            //  of heart rates
            val values: JSONArray = userHeartRateObject.getJSONArray("values")

            dayHeartRateList.add(DayHeartRate(date, createHeartRateList(values)))
        }

        return dayHeartRateList
    }

    // creates a list of heartRates from the given json array
    private fun createHeartRateList(array: JSONArray): ArrayList<HeartRate> {
        val heartRateList: ArrayList<HeartRate> = ArrayList()
        // Iterate over the values to get the heartRates
        for(j in 0 until array.length()){
        val obj:JSONObject = array.get(j) as JSONObject
            val min: String = obj.getString("min")

            heartRateList.add(HeartRate(
                obj.getString("min").toInt(),
                obj.getString("max").toInt(),
                obj.getString("name")))
        }

        return heartRateList
    }

}