package com.josemiz.globantcodechallenge.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.josemiz.globantcodechallenge.domain.model.EventsModel

class EventsModelProvider : PreviewParameterProvider<EventsModel> {
    override val values: Sequence<EventsModel>
        get() = sequenceOf(
            EventsModel(
                listOf(
                    PreviewParameterConstants.eventModel1,
                    PreviewParameterConstants.eventModel2
                )
            )
        )
}