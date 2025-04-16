package com.example.interviewtask.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.interviewtask.data.models.Stream

@Composable
fun FeedItemContent(stream: Stream,isMoreEnabled:Boolean=true) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FeedsProfileSection(stream.userDetails, stream.formatedTime,isMoreEnabled)
        FeedsContentSection(stream.content)
        ChipsSection(stream.tags)
        FeedsBottomSection(stream.viewCount)
        FeedsLikesAndComments(stream.likeCount, stream.comments.size.toString())
    }
}