package com.example.db_events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.db_events.network.EventsApi
import kotlinx.coroutines.launch

class EventsListViewModel : ViewModel() {
    private val _result = MutableLiveData<List<EventModel>>()
    val result: LiveData<List<EventModel>>
        get() = _result

    private val _locationFilter = MutableLiveData<Set<String>>()
    val locationFilter: LiveData<Set<String>>
        get() = _locationFilter

    init {
        getEventList()
        getLocationList()
    }

    private fun getEventList() {
        viewModelScope.launch {
            val eventsResponse = EventsApi.retrofitService.getEvents(token)
            if (eventsResponse.isNotEmpty()) {
                _result.value = eventsResponse
            } else {
                // TODO error handling
            }
        }
    }

    private fun getLocationList() {
        viewModelScope.launch {
            val eventsResponse = EventsApi.retrofitService.getLocationList(token)
            if (eventsResponse.isNotEmpty()) {
                _locationFilter.value = eventsResponse
            } else {
                // TODO error handling
            }
        }
    }
}