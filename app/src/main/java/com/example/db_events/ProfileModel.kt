package com.example.db_events

data class ProfileModel(
    var firstName: String,
    var lastName: String,
    var email: String,
    var pastEvents: List<EventModel>,
    var upcomingEvents: List<EventModel>,
)