package com.example.db_events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.db_events.network.EventsApi
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    private val _registerSuccessful = MutableLiveData<Boolean>()
    val registerSuccessful: LiveData<Boolean>
        get() = _registerSuccessful

    fun register(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            val eventsResponse = EventsApi.retrofitService.registerUser(registerRequest)
            _registerSuccessful.value = eventsResponse.isSuccessful
        }
    }
}