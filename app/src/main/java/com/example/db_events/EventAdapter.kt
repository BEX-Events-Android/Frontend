package com.example.db_events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.db_events.databinding.EventListViewBinding
import kotlin.random.Random

class EventAdapter(private val data: List<EventModel>, val callback: Callback) :
    RecyclerView.Adapter<EventAdapter.EventViewListHolder>() {

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
            ), callback
        )
    }

    interface Callback {
        fun onItemClicked(itemId: String)
    }

    class EventViewListHolder(private val binding: EventListViewBinding, val callback: Callback) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EventModel) {
            binding.eventTitle.text = item.title.replaceFirstChar { c -> c.uppercase() }
            binding.eventDate.text = item.startDateTime.split(" ")[0]
            binding.eventDescription.text = item.description
            binding.eventImage.setImageResource(photosList[Random.nextInt(0, photosList.size)])

            binding.eventListItem.setOnClickListener {
                callback.onItemClicked(item.id)
            }
        }
    }
}