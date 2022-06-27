package com.example.db_events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BookEventViewModelFactory(private val eventId: String): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookEventViewModel::class.java)) {
            return BookEventViewModel(eventId) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}