package com.example.db_events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.db_events.network.EventsApi
import kotlinx.coroutines.launch

class BookEventViewModel(private val eventId: String): ViewModel() {
    init {
        bookEvent()
    }

    private fun bookEvent() {
        viewModelScope.launch {
            EventsApi.retrofitService.bookEvent(eventId, token)
        }
    }
}