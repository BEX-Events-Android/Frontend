package com.example.db_events

import com.squareup.moshi.Json

data class EventModel(
    var id: String,
    @Json(name = "name") var title: String,
    var startDateTime: String,
    var endDateTime: String,
    var description: String,
    var location: String,
    var duration: String,
    var organiser: UserModel,
    var attendingEvent: Boolean
)