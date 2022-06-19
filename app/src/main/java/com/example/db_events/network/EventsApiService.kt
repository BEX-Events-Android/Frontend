package com.example.db_events.network

import android.media.metrics.Event
import com.example.db_events.EventModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_ULR = "http://localhost:8081/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_ULR)
    .build()

interface EventsApiService {
    @GET("events")
    fun getEvents():
            Deferred<List<EventModel>>
}

object EventsApi {
    val retrofitService: EventsApiService by lazy {
        retrofit.create(EventsApiService::class.java)
    }
}