package com.example.interviewtask.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.interviewtask.R
import com.example.interviewtask.data.models.Comment
import com.example.interviewtask.ui.utils.formatCount
import com.example.interviewtask.ui.utils.getStream

@Composable
fun CommentsSectionBottomActions(comment: Comment, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
//            modifier = Modifier.weight(.7f)

        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_like),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            VerticalDivider(
                modifier = Modifier.height(12.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant

            )
            Icon(
                painter = painterResource(id = R.drawable.ic_reply),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = stringResource(
                    R.string.replies,
                    (comment.replyCount.toFloatOrNull() ?: 0.0f).formatCount()
                ),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis

            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.weight(.3f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_liked),
                contentDescription = "",
                modifier = Modifier
                    .size(16.dp)
                    .background(color = Color.White, shape = CircleShape)
                    .padding(1.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_emoji_heart),
                contentDescription = "",
                modifier = Modifier
                    .offset(x = -4.dp)
                    .size(16.dp)
                    .background(color = Color.White, shape = CircleShape)
                    .padding(1.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_emoji_surprised),
                contentDescription = "",
                modifier = Modifier
                    .offset(x = -8.dp)
                    .size(16.dp)
                    .background(color = Color.White, shape = CircleShape)
                    .padding(1.dp)

            )
            Text(
                text = (comment.likeCount.toFloatOrNull() ?: 0.0f).formatCount(),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCommentsSectionBottomActions(){
    CommentsSectionBottomActions(comment = getStream(LocalContext.current).comments.get(0))
}
