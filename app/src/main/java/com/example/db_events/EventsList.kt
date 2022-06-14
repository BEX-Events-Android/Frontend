package com.example.db_events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.db_events.databinding.FragmentEventsListBinding
import com.example.db_events.databinding.FragmentLoginBinding

class EventsList : Fragment() {
    private lateinit var binding: FragmentEventsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEventsListBinding.inflate(inflater, container, false)

        val adapter = EventAdapter()
        binding.eventsList.adapter = adapter

        return binding.root
    }

}