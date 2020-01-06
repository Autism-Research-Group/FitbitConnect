package com.example.fitbitconnect.Activities

import android.content.ContentValues
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fitbitconnect.Database.*
import com.example.fitbitconnect.HeartRateItemAdaptor
import com.example.fitbitconnect.WebFetches.FetchAllHeartRates
import com.example.fitbitconnect.R
import com.example.fitbitconnect.models.DayHeartRate
import com.example.fitbitconnect.models.HeartRate
import com.example.fitbitconnect.models.User
import kotlinx.android.synthetic.main.activity_heartrate_all.*
import java.net.URL
import kotlin.math.min

class AllHeartRateActivity: AppCompatActivity() {

    lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heartrate_all)

        // Update the recycler view
        FetchAllHeartRates(this).execute(URL("http://10.0.2.2:3000/android/heartRate/period/all?range=1m"))

        btn_addData.setOnClickListener {
            val numberOfData: Int? = recycler_view.adapter?.itemCount
            // Try to add the data to the database if the data is not null
            if (numberOfData != null && numberOfData!! > 0) {
                addDataToDatabase(recycler_view.adapter as HeartRateItemAdaptor)
                Toast.makeText(this, "Data was successfully added to the database", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No Data was available to be added", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Adds the data that is currently present in the recycler view to the SQLite Database
    private fun addDataToDatabase(adaptor: HeartRateItemAdaptor) {
        val dbHelper = DatabaseHelper(this)
        db = dbHelper.writableDatabase
        dbHelper.rebuildDB(db)
        // The users current stored in the database
        val currentUserList: DatabaseUserList = getCurrentUsers(db)

        for( i in 0 until adaptor.itemCount) {
            val dataSet = adaptor.getData(i)
            // Create a user obj for the database
            val newUser = DatabaseUser(-1, dataSet.username, dataSet.userID)
            // Get the fk_id of the new user from the currentUserList
            val fk_id = currentUserList.getIndex(newUser)
            handleDBInsert(newUser, fk_id, dataSet.heartRateList, dataSet.dateTime)
        }
    }

    // Handles the data inserts for a user.
    private fun handleDBInsert(user: DatabaseUser, index: Int, data: ArrayList<HeartRate>, dateTime: String) {
        // Check if the index is -1. If so then the user is not already in the db. So the user must
        //   be added and the id must be returned
        var fk_id: Long = index.toLong()
        if(index == -1){
            val values = createUserDBValuesMap(user.username, user.userID)
            fk_id = insertUser(values)
        }
        // Add data sets for the user
        addUserHeartRateData(data, dateTime, fk_id)
    }

    // loops though all the users heart rate data and adds each set to the database
    private fun addUserHeartRateData(data: ArrayList<HeartRate>, dateTime: String, i: Long) {
        for(hr: HeartRate in data) {
            // create the heartRateData insert query
            val insert = createHeartRateDBValuesMap(hr, i, dateTime)
            // Add to the database
            insertHeartRateData(insert)
        }
    }

    // Adds the user to the database
    private fun insertUser(insert: ContentValues): Long {
        return try{
            db.insertOrThrow(UserContract.UserEntry.TABLE_NAME,null, insert)
        } catch (error: SQLiteConstraintException){
            println("There was an error with the User data insert: $error")
            -1
        }
    }

    // Adds a heartRateData to the database
    private fun insertHeartRateData(insert: ContentValues) : Long {
        return try {
            db.insertOrThrow(HeartRateContract.HeartRateEntry.TABLE_NAME,null, insert)
        } catch (error: SQLiteConstraintException) {
            println("There was an error with the Heartrate data insert: $error")
            -1
        }
    }



    // Returns a mutable list of all the current users
    private fun getCurrentUsers(db: SQLiteDatabase): DatabaseUserList {
        val projection = arrayOf(BaseColumns._ID, UserContract.UserEntry.COLUMN_NAME_USERNAME,
            UserContract.UserEntry.COLUMN_USERID)

        val cursor = db.query(
            UserContract.UserEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null)

        val users = DatabaseUserList()
        with(cursor) {
            while (moveToNext()) {
                val user = DatabaseUser(
                    getInt(getColumnIndex(BaseColumns._ID)),
                    getString(getColumnIndex(UserContract.UserEntry.COLUMN_NAME_USERNAME)),
                    getString(getColumnIndex(UserContract.UserEntry.COLUMN_USERID))
                )

                // Since each usr is unique we don't have to worry about duplicates
                users.add(user)
            }
        }
        return users
    }

    // Creates a UserEntry with the given values. This entry is used to make an insert into the database
    //   for the User table
    private fun createUserDBValuesMap(username: String, userID: String): ContentValues{
        val values = ContentValues().apply {
            put(UserContract.UserEntry.COLUMN_NAME_USERNAME, username)
            put(UserContract.UserEntry.COLUMN_USERID, userID)
        }
        return values
    }


    // Creates a HeartRateEntry with the given values. This entry is used to make an insert into the database
    //   for the HeartRate table
    private fun createHeartRateDBValuesMap(hrObj: HeartRate, userIndex: Long, dateTime: String): ContentValues {
        val values = ContentValues().apply {
            put(HeartRateContract.HeartRateEntry.COLUMN_NAME, hrObj.name)
            put(HeartRateContract.HeartRateEntry.COLUMN_MAX, hrObj.max)
            put(HeartRateContract.HeartRateEntry.COLUMN_MIN, hrObj.min)
            put(HeartRateContract.HeartRateEntry.COLUMN_DATETIME, dateTime)
            put(HeartRateContract.HeartRateEntry.COLUMN_USER_ID, userIndex)
        }
        return values
    }
}