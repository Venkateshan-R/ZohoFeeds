package com.example.interviewtask.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.interviewtask.data.models.Comment
import com.example.interviewtask.ui.utils.getStream

@Composable
fun CommentsCard(comment: Comment, onClick: () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        FeedsProfileSection(
            comment.userDetails,
            comment.formatedTime,
            onClick = onClick,
            isConnectMobileShouldShow = false
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(50.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Spacer(Modifier)
                FeedsContentSection(comment.content)
                CommentsSectionBottomActions(comment)
                Spacer(Modifier)
            }
        }
        HorizontalDivider(
            thickness = 0.5.dp,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(start = 40.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCommandsCard(){
    CommentsCard(comment = getStream(LocalContext.current).comments.get(0)) {

    }
}