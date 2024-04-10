package com.josemiz.globantcodechallenge.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.atLeast
import androidx.constraintlayout.compose.atMost
import coil.compose.AsyncImage
import com.josemiz.globantcodechallenge.domain.model.EventModel
import com.josemiz.globantcodechallenge.ui.preview.EventModelProvider
import com.josemiz.globantcodechallenge.ui.theme.GlobantCodeChallengeTheme

@Composable
fun TicketItem(modifier: Modifier = Modifier, eventModel: EventModel) {
    ConstraintLayout(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.White)
            .padding(end = 12.dp)
    ) {
        val (image, title, date, location, address) = createRefs()
        AsyncImage(
            model = eventModel.image,
            contentDescription = "",
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .padding(end = 12.dp)
                .constrainAs(image) {
                    linkTo(top = parent.top, bottom = parent.bottom)
                    start.linkTo(parent.start)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                        .atLeast(120.dp)
                        .atMost(200.dp)
                })
        Text(
            text = eventModel.title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.constrainAs(title) {
                linkTo(start = image.end, end = parent.end)
                top.linkTo(parent.top)
                width = Dimension.fillToConstraints
            })
        Text(
            text = eventModel.date,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.constrainAs(date) {
                linkTo(start = image.end, end = parent.end)
                top.linkTo(title.bottom)
                width = Dimension.fillToConstraints
            })
        Text(
            text = eventModel.location,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.constrainAs(location) {
                linkTo(start = image.end, end = parent.end)
                top.linkTo(date.bottom)
                width = Dimension.fillToConstraints
            })
        Text(
            text = eventModel.address,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.constrainAs(address) {
                linkTo(start = image.end, end = parent.end)
                top.linkTo(location.bottom)
                width = Dimension.fillToConstraints
            })
    }
}

@Preview
@Composable
private fun Preview(
    @PreviewParameter(EventModelProvider::class) eventModel: EventModel
) {
    GlobantCodeChallengeTheme {
        TicketItem(eventModel = eventModel, modifier = Modifier.fillMaxWidth())
    }
}