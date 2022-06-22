package com.example.db_events

import com.squareup.moshi.Json

data class EventModel(
    var id: String,
    @Json(name = "name") var name: String,
    var startDateTime: String,
    var description: String,
    var location: String = ""
)