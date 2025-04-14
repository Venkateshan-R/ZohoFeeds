package com.example.interviewtask.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.interviewtask.R
import com.example.interviewtask.data.models.UserDetails
import com.example.interviewtask.ui.theme.InterviewTaskTheme

@Composable
fun FeedsProfileSection(
    userDetails: UserDetails,
    formattedTime: String,
    isMoreEnabled: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .weight(.9f), verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(36.dp)
                    .weight(.1f, fill = true),
                model = userDetails.imageUrl,
                contentDescription = "",
                placeholder = painterResource(R.drawable.ic_profile)

            )

            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .weight(.8f, fill = true)
            ) {
                Text(text = userDetails.name)
                Row(verticalAlignment = Alignment.Top) {
                    Text(text = "connected", modifier = Modifier.padding(horizontal = 2.dp))
                    Text(
                        text = "•",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 2.dp)
                    )
                    Text(text = formattedTime, modifier = Modifier.padding(horizontal = 2.dp))
                }
            }
        }

        if(isMoreEnabled)
        Icon(
            painter = painterResource(id = R.drawable.ic_h_dot),
            contentDescription = "",
            modifier = Modifier.weight(.1f, fill = true)
        )

    }
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewFeedsProfile() {
    /* InterviewTaskTheme {
     FeedsProfileSection()
     }*/

}