package com.example.db_events.network

import android.media.metrics.Event
import com.example.db_events.EventModel
import com.example.db_events.LoginRequest
import com.example.db_events.RegisterRequest
import com.example.db_events.UserModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


private const val BASE_URL = "http://10.0.2.2:8080/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .client(OkHttpClient().newBuilder().build())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface EventsApiService {
    @GET("events")
    suspend fun getEvents(@Header("Cookie") token: String):
            List<EventModel>

    @GET("events/{id}")
    suspend fun getEvent(@Path("id") id: String, @Header("Cookie") token: String):
            EventModel

    @POST("users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest):
            Response<UserModel>

    @POST("users/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest):
            Response<ResponseBody>

    @POST("events/{id}/booking")
    suspend fun bookEvent(@Path("id") id: String, @Header("Cookie") token: String):
            Response<ResponseBody>
}

object EventsApi {
    val retrofitService: EventsApiService by lazy {
        retrofit.create(EventsApiService::class.java)
    }
}
