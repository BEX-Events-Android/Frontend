package com.example.db_events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class EventModel(val name: String, val date: String, val description: String) {
    
}

class EventAdapter: RecyclerView.Adapter<EventAdapter.EventViewListHolder>() {
    var data = listOf<EventModel>(EventModel("event1", "03/05/20", "description1"),
        EventModel("event2", "04/05/21", "description2"),
        EventModel("event3", "05/05/21", "description3"),
        EventModel("event2", "04/05/21", "description2"),
        EventModel("event3", "05/05/21", "description3"),
        EventModel("event2", "04/05/21", "description2"),
        EventModel("event3", "05/05/21", "description3"),
        EventModel("event2", "04/05/21", "description2"),
        EventModel("event3", "05/05/21", "description3"),
        EventModel("event2", "04/05/21", "description2"),
        EventModel("event3", "05/05/21", "description3"),
        EventModel("event2", "04/05/21", "description2"),
        EventModel("event3", "05/05/21", "description3"),
    )
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: EventViewListHolder, position: Int) {
        val item: EventModel = data[position]
//        val res = holder.itemView.context.resources

        holder.eventName.text = item.name.toString()
        holder.eventDate.text = item.date.toString()
        holder.eventDescription.text = item.description.toString()
//        holder.eventImage.setImageResource(when (item.name.length > 5) {
//            true -> R.drawable.star
//        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.event_list_view, parent, false)
        return EventViewListHolder(view)
    }

    class EventViewListHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val eventName: TextView = itemView.findViewById(R.id.event_name)
        val eventDate: TextView = itemView.findViewById(R.id.event_date)
        var eventDescription: TextView = itemView.findViewById(R.id.event_description)
//        val eventImage: ImageView = itemView.findViewById(R.id.event_image)
    }
}