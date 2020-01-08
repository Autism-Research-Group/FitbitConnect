package com.example.fitbitconnect.WebFetches

import android.app.Activity
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitbitconnect.Activities.AllHeartRateActivity
import com.example.fitbitconnect.HeartRateItemAdaptor
import com.example.fitbitconnect.models.DayHeartRate
import com.example.fitbitconnect.models.HeartRate
import kotlinx.android.synthetic.main.activity_heartrate_all.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import kotlin.collections.ArrayList

class FetchAllHeartRates(context: Activity): FetchGetUrl<ArrayList<DayHeartRate>>(context) {

    override fun onPostExecute(result: ArrayList<DayHeartRate>) {
        val activity: AllHeartRateActivity = activityReference.get() as AllHeartRateActivity
        if(activity != null) {
            val recyclerView = activity.recycler_view
            recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            val adapter = HeartRateItemAdaptor(result)
            recyclerView.adapter = adapter
        }
    }

    override fun handleResponse(data: String, url: String): ArrayList<DayHeartRate> {
        val heartRateList: ArrayList<DayHeartRate> = ArrayList()

        val dataArray = JSONArray(data)
        Log.i("JSON", heartRateList.toString())


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
            val dayList = createDayHeartRateList(user, username, userID)

            // Create and add the user to the user list
            heartRateList.addAll(dayList)
        }
       // Log.i("JSON", heartRateList.toString())

        return heartRateList
    }


    // Creates the list of DayHeartRates using the given json array
    private fun createDayHeartRateList(array: JSONArray, username: String, userID: String): ArrayList<DayHeartRate> {

        val dayHeartRateList = ArrayList<DayHeartRate>()
        for(i in 0 until array.length()){
            val userHeartRateObject: JSONObject = array.get(i) as JSONObject
            // The datetime
            val date = userHeartRateObject.getString("dateTime")
            // The array that will be passed to createHeartRateList() to create the list
            //  of heart rates
            val values: JSONObject = userHeartRateObject.getJSONObject("value")

            dayHeartRateList.add(DayHeartRate(username, userID, date, createHeartRateList(values)))
        }

        return dayHeartRateList
    }

    // creates a list of heartRates from the given json array
    private fun createHeartRateList(obj: JSONObject): ArrayList<HeartRate> {
        Log.i("JSON", obj.toString())

        val array = obj.getJSONArray("heartRateZones") // array of heart rate data

        val heartRateList: ArrayList<HeartRate> = ArrayList()
        // Iterate over the values to get the heartRates
        for(j in 0 until array.length()){
            val obj2:JSONObject = array.get(j) as JSONObject
            heartRateList.add(HeartRate(
                obj2.getString("min").toInt(),
                obj2.getString("max").toInt(),
                obj2.getString("name")))
        }

        return heartRateList
    }

}