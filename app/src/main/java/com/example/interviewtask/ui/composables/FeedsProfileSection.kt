package com.example.interviewtask.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.interviewtask.R
import com.example.interviewtask.data.models.UserDetails
import com.example.interviewtask.ui.theme.InterviewTaskTheme
import com.example.interviewtask.ui.theme.customFontFamily
import com.example.interviewtask.ui.utils.getStream

@Composable
fun FeedsProfileSection(
    userDetails: UserDetails,
    formattedTime: String,
    isMoreEnabled: Boolean = true,
    isConnectMobileShouldShow: Boolean = true,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(.9f), verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape),
                model = userDetails.imageUrl,
                contentDescription = "",
                placeholder = painterResource(R.drawable.ic_profile)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Column(
                modifier = Modifier
            ) {
                Text(
                    text = userDetails.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (isConnectMobileShouldShow) {
                        Text(
                            text = stringResource(R.string.connect_mobile), style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1, overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = stringResource(R.string.dot),
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant

                        )
                    }
                    Text(
                        text = formattedTime.split(",").get(0),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.outlineVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        if (isMoreEnabled) IconButton(onClick = { onClick.invoke() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_h_dot),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.outline
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewFeedsProfile() {
    InterviewTaskTheme {
        FeedsProfileSection(
            getStream(LocalContext.current).userDetails,
            getStream(LocalContext.current).formatedTime
        )
    }

}