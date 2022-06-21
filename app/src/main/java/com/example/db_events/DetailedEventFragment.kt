package com.example.db_events

import android.view.View
import androidx.fragment.app.Fragment
import com.example.db_events.databinding.FragmentDetailedEventBinding
import com.example.db_events.network.EventsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailedEventFragment : Fragment() {
    private lateinit var binding: FragmentDetailedEventBinding
    private lateinit var id: String

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: android.os.Bundle?
    ): View? {
        binding = FragmentDetailedEventBinding.inflate(inflater, container, false)

        id = arguments?.getString("id")!!
        println("TEST print" + id)

        getEvent()

        return binding.root
    }

    fun getEvent() {

        coroutineScope.launch {
            val eventsResponse = EventsApi.retrofitService.getEvent(id)
            println("in detailed event")
            println(eventsResponse)

            with (eventsResponse) {
                binding.eventDate.text = this.startDateTime
                binding.eventDescription.text = this.description
                binding.eventTitle.text = this.name
                binding.eventLocation.text = this.location
            }
        }
    }
}