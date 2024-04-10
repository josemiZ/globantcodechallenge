package com.josemiz.globantcodechallenge.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.josemiz.globantcodechallenge.domain.model.EventModel

class EventModelProvider : PreviewParameterProvider<EventModel> {
    override val values: Sequence<EventModel>
        get() = sequenceOf(PreviewParameterConstants.eventModel1)
}