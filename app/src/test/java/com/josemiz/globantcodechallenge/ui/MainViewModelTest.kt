package com.josemiz.globantcodechallenge.ui

import android.util.Log
import app.cash.turbine.test
import com.josemiz.globantcodechallenge.domain.model.EventModel
import com.josemiz.globantcodechallenge.domain.model.EventsModel
import com.josemiz.globantcodechallenge.domain.usecase.EventsUseCase
import com.josemiz.globantcodechallenge.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        mockkStatic(Log::class)
        every { Log.e(any(), any()) } returns 0

        val eventsUseCase = mockk<EventsUseCase> {
            coEvery { getEvents(any()) } returns EventsModel(listOf(EventModel(
                "",
                "Coldplay",
                "2023-10-12",
                "Estadio Nacional",
                "Lima, PE"
            )))
        }
        mainViewModel = MainViewModel(eventsUseCase)
    }

    @Test
    fun `Get Events`() = runBlocking {
        mainViewModel.loadEvents()
        mainViewModel.searchResults.test {
            val events = awaitItem()
            assert(events.events.first().title == "Coldplay")
        }
    }

    @Test
    fun `Search Event successfully`() = runBlocking {
        mainViewModel.loadEvents()
        mainViewModel.searchEvents("C")
        mainViewModel.searchResults.test {
            val events = awaitItem()
            assert(events.events.first().title == "Coldplay")
        }
    }

    @Test
    fun `Search Event empty`() = runBlocking {
        mainViewModel.loadEvents()
        mainViewModel.searchEvents("AJAX")
        mainViewModel.searchResults.test {
            val events = awaitItem()
            assert(events.events.isEmpty())
        }
    }
}