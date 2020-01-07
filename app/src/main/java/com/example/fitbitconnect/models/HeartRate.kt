package com.example.fitbitconnect.models

import java.io.Serializable

/**
 * DayHeartRate is an object that represents User that is stored in the backend server. This object
 *   also includes all the hear rates associated with the user. The values needed are:
 *      - username: The Name of the user
 *      - userID: The Fitbit user id associated with the user
 *      - dataTime: The date associated with the heart rate data
 *      - heartRateList: A list of heartRate objects (See HearRate for more info)
 *
 * This class extends Serializable so that it has the ability to be passed to different intents and
 *   activities.
 */
data class DayHeartRate(val username: String, val userID: String, val dateTime: String,
                   val heartRateList: ArrayList<HeartRate>) : Serializable


/**
 * HeartRate is an object that represents a single Fitbit heart rate data point. The values needed are:
 *      - name: The type of heart rate
 *      - max: The max heart rate recorded for the type
 *      - min: The min heart rate recorded for the type
 *
 * This class extends Serializable so that it has the ability to be passed to different intents and
 *   activities.
 */
class HeartRate(val min: Int, val max: Int, val name: String): Serializable

