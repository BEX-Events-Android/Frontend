package com.example.db_events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.db_events.network.EventsApi
import kotlinx.coroutines.launch

class DetailedEventViewModel(private val eventId: String) : ViewModel() {
    private val _result = MutableLiveData<EventModel>()
    val result: LiveData<EventModel>
        get() = _result

    private val _bookingSuccessful = MutableLiveData<Boolean>()
    val bookingSuccessful: LiveData<Boolean>
        get() = _bookingSuccessful

    init {
        getEvent()
    }

    private fun getEvent() {
        viewModelScope.launch {
            val eventsResponse = EventsApi.retrofitService.getEvent(eventId, token)
            if (eventsResponse.id.isNotEmpty()
                && eventsResponse.title.isNotEmpty()
                && eventsResponse.description.isNotEmpty()
                && eventsResponse.startDateTime.isNotEmpty()
                && eventsResponse.endDateTime.isNotEmpty()
                && eventsResponse.location.isNotEmpty()
            ) {
                _result.value = eventsResponse
            } else {
                // TODO error handling
            }
        }
    }

    fun bookEvent(eventId: String) {
        viewModelScope.launch {
            val eventsResponse = EventsApi.retrofitService.bookEvent(eventId, token)
            _bookingSuccessful.value = eventsResponse.isSuccessful
        }
    }
}