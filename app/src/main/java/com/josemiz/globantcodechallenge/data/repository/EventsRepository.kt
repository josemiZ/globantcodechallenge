package com.josemiz.globantcodechallenge.data.repository

import com.josemiz.globantcodechallenge.data.remote.response.DataResponse

interface EventsRepository {
    suspend fun getEvents(apiKey: String): DataResponse
}