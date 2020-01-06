package com.example.fitbitconnect

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitbitconnect.Activities.DetailedHeartRateActivity
import com.example.fitbitconnect.models.DayHeartRate
import com.example.fitbitconnect.models.HeartRate
import kotlinx.android.synthetic.main.heartrate_item.view.*

class HeartRateItemAdaptor(val heartRateList: ArrayList<DayHeartRate>): RecyclerView.Adapter<HeartRateItemAdaptor.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.heartrate_item, parent, false) as CardView
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userData: DayHeartRate = heartRateList[position]
        holder.username.text = userData.username
        holder.userID.text = userData.userID
        holder.dateTime.text = userData.dateTime
        holder.data = userData.heartRateList

        holder.cardView.setOnClickListener {
            val intent: Intent = Intent(context, DetailedHeartRateActivity::class.java)
            intent.putExtra("User", userData)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return heartRateList.size
    }

    /**
     * Returns the DayHeartRate object at the index
     */
    fun getData(index: Int) : DayHeartRate {
        return heartRateList.get(index)
    }



    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.card_view
        val username: TextView = itemView.username
        val userID: TextView = itemView.user_id
        val dateTime: TextView = itemView.datetime
        lateinit var data: ArrayList<HeartRate>
    }
}