package com.josemiz.globantcodechallenge.data.remote.response

import com.squareup.moshi.Json

data class VenueResponse(
    @Json(name = "id") val id: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "state") val state: StateResponse?,
    @Json(name = "address") val address: AddressResponse?,
)

data class StateResponse(
    @Json(name = "name") val name: String?,
    @Json(name = "stateCode") val code: String?,
)

data class AddressResponse(
    @Json(name = "line1") val address: String?,
)
