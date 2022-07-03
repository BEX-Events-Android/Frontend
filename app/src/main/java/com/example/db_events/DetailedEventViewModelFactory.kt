package com.example.db_events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailedEventViewModelFactory(private val eventId: String): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailedEventViewModel::class.java)) {
            return DetailedEventViewModel(eventId) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }

}