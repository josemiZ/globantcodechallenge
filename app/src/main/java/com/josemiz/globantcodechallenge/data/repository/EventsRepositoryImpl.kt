package com.josemiz.globantcodechallenge.data.repository

import com.josemiz.globantcodechallenge.data.remote.connection.EventsConnection
import com.josemiz.globantcodechallenge.data.remote.response.DataResponse
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val eventsConnection: EventsConnection
) : EventsRepository {
    override suspend fun getEvents(apiKey: String): DataResponse {
        return eventsConnection.getEvents(apiKey)
    }
}