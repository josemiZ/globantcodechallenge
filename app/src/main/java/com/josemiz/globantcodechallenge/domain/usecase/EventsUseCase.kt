package com.josemiz.globantcodechallenge.domain.usecase

import com.josemiz.globantcodechallenge.data.remote.response.ImageResponse
import com.josemiz.globantcodechallenge.data.remote.response.VenuesResponse
import com.josemiz.globantcodechallenge.data.repository.EventsRepository
import com.josemiz.globantcodechallenge.domain.model.EventModel
import com.josemiz.globantcodechallenge.domain.model.EventsModel
import javax.inject.Inject

class EventsUseCase @Inject constructor(
    private val eventsRepository: EventsRepository
) {

    suspend fun getEvents(apiKey: String): EventsModel {
        return eventsRepository.getEvents(apiKey).let {
            EventsModel(
                it.embedded.events.map { event ->
                    EventModel(
                        getImageByRatio(event.images),
                        event.name.orEmpty(),
                        event.dates?.startDate?.date.orEmpty(),
                        getLocationName(event.embedded),
                        getAddress(event.embedded)
                    )
                }
            )
        }
    }

    private fun getImageByRatio(list: List<ImageResponse>): String {
        return list.find { it.width > 100 }?.url.orEmpty()
    }

    private fun getLocationName(venuesResponse: VenuesResponse?): String {
        return venuesResponse?.venues?.firstOrNull()?.name.orEmpty()
    }

    private fun getAddress(venuesResponse: VenuesResponse?): String {
        return venuesResponse?.venues?.firstOrNull()?.address?.address.orEmpty()
    }
}