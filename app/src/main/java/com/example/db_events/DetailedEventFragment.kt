package com.example.db_events

import android.view.View
import androidx.fragment.app.Fragment
import com.example.db_events.databinding.FragmentDetailedEventBinding

class DetailedEventFragment : Fragment() {
    private lateinit var binding: FragmentDetailedEventBinding

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: android.os.Bundle?
    ): View? {
        binding = FragmentDetailedEventBinding.inflate(inflater, container, false)

        return binding.root
    }
}