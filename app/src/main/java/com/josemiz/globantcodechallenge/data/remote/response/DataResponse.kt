package com.josemiz.globantcodechallenge.data.remote.response

import com.squareup.moshi.Json

data class DataResponse(
    @Json(name = "_embedded") val embedded: EventsResponse,
)

data class EventsResponse(
    @Json(name = "events") val events: List<EventResponse>
)

