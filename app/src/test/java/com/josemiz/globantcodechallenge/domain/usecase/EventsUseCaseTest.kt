package com.josemiz.globantcodechallenge.domain.usecase

import com.josemiz.globantcodechallenge.data.remote.response.AddressResponse
import com.josemiz.globantcodechallenge.data.remote.response.DataResponse
import com.josemiz.globantcodechallenge.data.remote.response.DateResponse
import com.josemiz.globantcodechallenge.data.remote.response.EventResponse
import com.josemiz.globantcodechallenge.data.remote.response.EventsResponse
import com.josemiz.globantcodechallenge.data.remote.response.ImageResponse
import com.josemiz.globantcodechallenge.data.remote.response.StartDateResponse
import com.josemiz.globantcodechallenge.data.remote.response.StateResponse
import com.josemiz.globantcodechallenge.data.remote.response.VenueResponse
import com.josemiz.globantcodechallenge.data.remote.response.VenuesResponse
import com.josemiz.globantcodechallenge.data.repository.EventsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class EventsUseCaseTest {
    private lateinit var eventsUseCase: EventsUseCase
    private val eventsRepository = mockk<EventsRepository> {
        coEvery { getEvents("") } returns DataResponse(EventsResponse(
            listOf(EventResponse(
                "The Taylor Party: Taylor Swift Night",
                null,
                null,
                false,
                null,
                DateResponse(
                    StartDateResponse("2016-06-06"),
                    null
                ),
                null,
                listOf(ImageResponse(
                    null,
                    null,
                    300,
                    300,
                    false
                )),
                VenuesResponse(listOf(VenueResponse(
                    null,
                    "Theatre of Living Arts",
                    StateResponse("Philadelphia", "PA"),
                    AddressResponse("Philadelphia, PA")
                )))
            ))
        ))
    }

    @Before
    fun setup() {
        eventsUseCase = EventsUseCase(eventsRepository)
    }

    @Test
    fun `Get Event List`() = runBlocking {
        val events = eventsUseCase.getEvents("")
        assert(events.events.first().title == "The Taylor Party: Taylor Swift Night")
        assert(events.events.first().image.isEmpty())
    }

    @Test
    fun `Get Empty List`() = runBlocking {
        coEvery { eventsRepository.getEvents("") } returns DataResponse(EventsResponse(emptyList()))
        val events = eventsUseCase.getEvents("")
        assert(events.events.isEmpty())
    }
}