package com.josemiz.globantcodechallenge.data.remote.response

import com.squareup.moshi.Json

data class DateResponse(
    @Json(name = "start") val startDate: StartDateResponse?,
    @Json(name = "timezone") val timezone: String?,
)

data class StartDateResponse(
    @Json(name = "localDate") val date: String?,
)
