package com.example.fitbitconnect

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitbitconnect.Activities.UserListActivity
import com.example.fitbitconnect.models.User
import kotlinx.android.synthetic.main.activity_userlist.*
import org.json.JSONObject
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class FetchAllUsers(context: UserListActivity): FetchGetUrl<ArrayList<User>>(context) {

    override fun doInBackground(vararg url: URL): ArrayList<User>  {
        val httpURLConnection = createHttpURLConnection(url[0])

        return parseBody(httpURLConnection)
    }

    override fun onPostExecute(result: ArrayList<User>) {
        val activity: UserListActivity = activityReference.get() as UserListActivity
        if (activity != null) {
            val recyclerView = activity.recycler_view

            recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)


            val adapter = CustomAdapter(result)

            recyclerView.adapter = adapter
        }
    }

    /**
     * Parses the JSON object into userObjects
     */
    private fun parseBody(httpURLConnection: HttpURLConnection): ArrayList<User> {
        var userList = ArrayList<User>()

        try{
            val data: String = httpURLConnection.inputStream.bufferedReader().use{ it.readText() }

            // Get the main set of data

            val dataArray = JSONObject(data).getJSONArray("userList")

            for(i in 0 until dataArray.length()) {
                val obj: JSONObject = dataArray.get(i) as JSONObject
                // create the user object and add it to the arrayList
                userList.add(createUserObject(obj))
            }
        }

        catch (exception: Exception){
            throw Exception("There was an error parsing the data: $exception")

        }

        finally {
          httpURLConnection.disconnect()
            Log.i("JSON", "Here")
        }

        return userList
    }

    /**
     * Creates a User object from the given user JSON object
     */
    private fun createUserObject(obj: JSONObject): User {
        return User(obj.getString("username"), obj.getString("userID"))
    }
}