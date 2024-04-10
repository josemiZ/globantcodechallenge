package com.josemiz.globantcodechallenge.data.remote.response

import com.squareup.moshi.Json

data class EventResponse(
    @Json(name = "name") val name: String?,
    @Json(name = "type") val type: String?,
    @Json(name = "id") val id: String?,
    @Json(name = "test") val test: Boolean,
    @Json(name = "url") val url: String?,
    @Json(name = "dates") val dates: DateResponse?,
    @Json(name = "locale") val locale: String?,
    @Json(name = "images") val images: List<ImageResponse> = emptyList(),
    @Json(name = "_embedded") val embedded: VenuesResponse?
)

data class VenuesResponse(
    @Json(name = "venues") val venues: List<VenueResponse> = emptyList()
)
