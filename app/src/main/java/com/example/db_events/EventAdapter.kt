package com.example.db_events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.db_events.databinding.EventListViewBinding

class EventAdapter(private val data: List<EventModel>): RecyclerView.Adapter<EventAdapter.EventViewListHolder>() {
//    var data = listOf<EventModel>(EventModel("event1", "03/05/20", "description1"),
//        EventModel("event2", "04/05/21", "description2"),
//        EventModel("event3", "05/05/21", "description3"),
//        EventModel("event2", "04/05/21", "description2"),
//        EventModel("event3", "05/05/21", "description3"),
//        EventModel("event2", "04/05/21", "description2"),
//        EventModel("event3", "05/05/21", "description3"),
//        EventModel("event2", "04/05/21", "description2"),
//        EventModel("event3", "05/05/21", "description3"),
//        EventModel("event2", "04/05/21", "description2"),
//        EventModel("event3", "05/05/21", "description3"),
//        EventModel("event2", "04/05/21", "description2"),
//        EventModel("event3", "05/05/21", "description3"),
//    )
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: EventViewListHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewListHolder {
        return EventViewListHolder(
            EventListViewBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.event_list_view,
                    parent,
                    false
                )
            )
        )
    }

    class EventViewListHolder(private val binding: EventListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EventModel) {
            binding.eventDate.text = item.startDateTime
            binding.eventDescription.text = item.description
            binding.eventNameTv.text = item.name

            binding.eventListItem.setOnClickListener{ view: View ->
                println("pressed id " + item.id)
                val bundle = bundleOf("id" to item.id)
                view.findNavController().navigate(R.id.action_eventsListFragment_to_detailedEventFragment, bundle)
            }
        }
    }
}