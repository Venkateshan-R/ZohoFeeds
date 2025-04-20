package com.example.interviewtask.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.interviewtask.R
import com.example.interviewtask.ui.utils.formatCount

@Composable
fun FeedsLikesAndComments(
    likesCount: String,
    commentsCount: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_liked),
            modifier = Modifier.size(18.dp),
            contentDescription = ""
        )
        Text(
            text = (likesCount.toFloatOrNull() ?: 0.0f).formatCount(),
            modifier = Modifier.padding(horizontal = 2.dp, vertical = 1.dp),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = stringResource(R.string.dot),
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp),

            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = stringResource(
                R.string.comments,
                (commentsCount.toFloatOrNull() ?: 0.0f).formatCount()
            ),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewFeedsLikesAndComments() {
        FeedsLikesAndComments(likesCount = "10", commentsCount = "5" )
}