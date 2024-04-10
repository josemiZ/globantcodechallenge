package com.josemiz.globantcodechallenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.josemiz.globantcodechallenge.domain.model.EventsModel
import com.josemiz.globantcodechallenge.ui.theme.GlobantCodeChallengeTheme
import com.josemiz.globantcodechallenge.ui.view.TicketsView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val events: EventsModel by viewModel.searchResults.collectAsStateWithLifecycle()
            GlobantCodeChallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TicketsView(
                        eventsModel = events,
                        onValueChange = { viewModel.searchEvents(it) },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadEvents()
    }
}