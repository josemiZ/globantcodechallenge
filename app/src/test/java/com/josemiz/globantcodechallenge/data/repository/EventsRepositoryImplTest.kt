package com.josemiz.globantcodechallenge.data.repository

import com.josemiz.globantcodechallenge.data.remote.connection.EventsConnection
import com.josemiz.globantcodechallenge.data.remote.response.DataResponse
import com.josemiz.globantcodechallenge.data.remote.response.EventsResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class EventsRepositoryImplTest {
    private lateinit var eventsRepositoryImpl: EventsRepositoryImpl

    @Before
    fun setup() {
        val eventsConnection = mockk<EventsConnection>() {
            coEvery { getEvents("") } returns DataResponse(EventsResponse(emptyList()))
        }
        eventsRepositoryImpl = EventsRepositoryImpl(eventsConnection)
    }

    @Test
    fun `Get event list`() = runBlocking {
        val events = eventsRepositoryImpl.getEvents("")
        assert(events.embedded.events.isEmpty())
    }
}