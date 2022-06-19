package com.example.db_events

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.db_events.databinding.FragmentEventsListBinding
import com.example.db_events.databinding.FragmentLoginBinding
import com.example.db_events.network.EventsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class EventsListFragment : Fragment() {
    private lateinit var binding: FragmentEventsListBinding

    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the request status String
    val response: LiveData<String>
        get() = _response

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEventsListBinding.inflate(inflater, container, false)

        val adapter = EventAdapter()
        binding.eventsList.adapter = adapter

        getEventsList()

        return binding.root
    }


    fun getEventsList() {

        coroutineScope.launch {
            var getEventsDeferred = EventsApi.retrofitService.getEvents()
            try {
                var listResult = getEventsDeferred.await()
                _response.value = "success"
            } catch (t:Throwable) {
                _response.value = "Failure: " + t.message
                println("RIPRIPRIPRIRPIPRIPR")
            }
        }
    }
    
}