package com.example.interviewtask.ui.composables

import android.content.res.Resources.Theme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.interviewtask.data.models.Stream
import com.example.interviewtask.ui.utils.getDummyData

@Composable
fun FeedsCard(stream: Stream, onclick: () -> Unit) {

    Card(
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContentColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable {
                onclick.invoke()
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        FeedItemContent(stream)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFeedsBottomSection() {
    FeedsCard(getDummyData(LocalContext.current)!!.recentStreams.streams.get(0)) {

    }
}

