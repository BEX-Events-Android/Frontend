package com.example.db_events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.db_events.network.EventsApi
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _result = MutableLiveData<List<EventModel>>()
    val result: LiveData<List<EventModel>>
        get() = _result

    private val _navigationEvent = MutableLiveData<Boolean>()
    val navigationEvent: LiveData<Boolean>
        get() = _navigationEvent

    init {
        getEventList()
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

    fun signOut() {
        viewModelScope.launch{
            val eventsResponse = EventsApi.retrofitService.signOut()
            _navigationEvent.value = eventsResponse.isSuccessful
        }
    }
}