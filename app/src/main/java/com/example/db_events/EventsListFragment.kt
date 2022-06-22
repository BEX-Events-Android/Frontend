package com.example.db_events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.db_events.databinding.FragmentEventsListBinding
import com.example.db_events.network.EventsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EventsListFragment : Fragment(), EventAdapter.Callback {
    private lateinit var binding: FragmentEventsListBinding

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventsListBinding.inflate(inflater, container, false)

        getEventsList()

        return binding.root
    }


    private fun getEventsList() {

        coroutineScope.launch {
            val eventsResponse = EventsApi.retrofitService.getEvents()
            if (eventsResponse.isNotEmpty()) {
                println(eventsResponse)
                binding.eventsList.adapter = EventAdapter(eventsResponse, this@EventsListFragment)
            } else {
                // TODO error handling
            }
        }
    }

    override fun onItemClicked(itemId: String) {
        val bundle = bundleOf("id" to itemId)
        view?.findNavController()
            ?.navigate(R.id.action_eventsListFragment_to_detailedEventFragment, bundle)
    }

}