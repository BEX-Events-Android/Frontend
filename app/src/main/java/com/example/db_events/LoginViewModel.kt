package com.example.db_events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.db_events.network.EventsApi
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val _result = MutableLiveData<UserModel>()
    val result: LiveData<UserModel>
        get() = _result

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            val eventsResponse = EventsApi.retrofitService.loginUser(loginRequest)
            println("CODE IS " + eventsResponse.code().toString())
            if (eventsResponse.code() != 200) {
                _error.value = "err"
                return@launch
            }

            _result.value = eventsResponse.body()
            val headerList = eventsResponse.headers()
            token = headerList.get("Cookie").toString()
        }
    }
}