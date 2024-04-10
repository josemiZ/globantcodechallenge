package com.josemiz.globantcodechallenge.data.remote.connection

import com.josemiz.globantcodechallenge.data.remote.response.DataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EventsConnection {

    @GET("events.json")
    suspend fun getEvents(@Query("apikey") apiKey: String): DataResponse
}