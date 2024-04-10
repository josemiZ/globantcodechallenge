package com.josemiz.globantcodechallenge.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.josemiz.globantcodechallenge.domain.model.EventsModel
import com.josemiz.globantcodechallenge.ui.preview.EventsModelProvider

@Composable
fun TicketsView(
    eventsModel: EventsModel,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") } // Query for SearchBar

    Scaffold(
        topBar = {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    onValueChange.invoke(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = {
                            searchQuery = ""
                            onValueChange.invoke("")
                        }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear Search"
                            )
                        }
                    }
                }
            )
        },
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(eventsModel.events) { ticket ->
                TicketItem(eventModel = ticket, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview(
    @PreviewParameter(EventsModelProvider::class) eventsModel: EventsModel
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TicketsView(eventsModel = eventsModel, onValueChange = {}, modifier = Modifier.fillMaxWidth())
    }
}