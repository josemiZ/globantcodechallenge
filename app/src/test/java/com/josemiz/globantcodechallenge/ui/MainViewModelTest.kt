package com.josemiz.globantcodechallenge.ui

import android.util.Log
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
            coEvery { getEvents("") } returns EventsModel(listOf(EventModel(
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
        val events = mainViewModel.searchResults.last()
        assert(events.events.first().title == "Coldplay")
    }
}