package com.example.fitbitconnect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitbitconnect.models.User
import kotlinx.android.synthetic.main.list_layout.view.*

class CustomAdapter(val userList: ArrayList<User>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: User = userList[position]

        holder.textViewUsername.text = user.username
        holder.textViewId.text = user.userID
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewUsername = itemView.txt_username
        val textViewId = itemView.txt_userID
    }
}