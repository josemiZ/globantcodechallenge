package com.josemiz.globantcodechallenge.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josemiz.globantcodechallenge.domain.model.EventsModel
import com.josemiz.globantcodechallenge.domain.usecase.EventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val eventsUseCase: EventsUseCase
) : ViewModel() {

    private val _events = MutableStateFlow(EventsModel(emptyList()))
    private var _searchQuery by mutableStateOf("")

    val searchResults: StateFlow<EventsModel> =
        snapshotFlow { _searchQuery }
            .combine(_events) { searchQuery, events ->
                when {
                    searchQuery.isNotEmpty() -> events.copy(events = events.events.filter { event ->
                            event.title.contains(searchQuery)
                        }
                    )
                    else -> events
                }
            }.stateIn(scope = viewModelScope, SharingStarted.WhileSubscribed(5_000), EventsModel(emptyList()))

    fun loadEvents() {
        viewModelScope.launch {
            try {
                _events.value = eventsUseCase.getEvents("DW0E98NrxUIfDDtNN7ijruVSm60ryFLX")
            } catch (e: Exception) {
                Log.e("events error", e.message.toString())
            }
        }
    }

    fun searchEvents(query: String) {
        _searchQuery = query
    }
}