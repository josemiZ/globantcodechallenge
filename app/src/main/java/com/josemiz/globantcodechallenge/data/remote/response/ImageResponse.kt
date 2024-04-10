package com.josemiz.globantcodechallenge.data.remote.response

import com.squareup.moshi.Json

data class ImageResponse(
    @Json(name = "ratio") val ratio: String?,
    @Json(name = "url") val url: String?,
    @Json(name = "width") val width: Int,
    @Json(name = "height") val height: Int,
    @Json(name = "fallback") val fallback: Boolean,
)
