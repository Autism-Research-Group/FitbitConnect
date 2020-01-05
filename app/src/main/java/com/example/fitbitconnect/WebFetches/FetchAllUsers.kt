package com.example.fitbitconnect.WebFetches

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitbitconnect.Activities.UserListActivity
import com.example.fitbitconnect.CustomAdapter
import com.example.fitbitconnect.models.User
import kotlinx.android.synthetic.main.activity_userlist.*
import org.json.JSONObject

class FetchAllUsers(context: UserListActivity): FetchGetUrl<ArrayList<User>>(context) {


    override fun onPostExecute(result: ArrayList<User>) {
        val activity: UserListActivity = activityReference.get() as UserListActivity
        if (activity != null) {
            // Add the data to the recycler view
            val recyclerView = activity.recycler_view
            recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            val adapter =
                CustomAdapter(result)
            recyclerView.adapter = adapter
        }
    }

    /**
     * Handles the response from the server
     */
    override fun handleResponse(data: String): ArrayList<User> {
        val dataArray = JSONObject(data).getJSONArray("userList")
        val userList: ArrayList<User> = ArrayList()

        for(i in 0 until dataArray.length()) {
            val obj: JSONObject = dataArray.get(i) as JSONObject
            // create the user object and add it to the arrayList
            userList.add(createUserObject(obj))
        }

        return userList
    }


    // Creates a User object from the given user JSON object
    private fun createUserObject(obj: JSONObject): User {
        return User(obj.getString("username"), obj.getString("userID"))
    }
}