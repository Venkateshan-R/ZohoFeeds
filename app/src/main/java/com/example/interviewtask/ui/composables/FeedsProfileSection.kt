package com.example.interviewtask.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(.9f), verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape),
                model = userDetails.imageUrl,
                contentDescription = "",
                placeholder = painterResource(R.drawable.ic_profile)
            )

            Column(
                modifier = Modifier.weight(.1f, fill = true).padding(horizontal = 6.dp)
            ) {
                Text(
                    text = userDetails.name,
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    lineHeight = 18.sp

                )
                Row(verticalAlignment = Alignment.Top) {
                    if (isConnectMobileShouldShow) {
                        Text(
                            text = "Connect mobile",
                            fontFamily = customFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = Color.Black.copy(alpha = 0.5f),
                            lineHeight = 18.sp
                        )
                        Text(
                            text = "•",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(horizontal = 4.dp),
                            fontFamily = customFontFamily,
                            fontWeight = FontWeight.Light,
                            fontSize = 16.sp,
                            color = Color.Black.copy(alpha = 0.5f),
                            lineHeight = 18.sp
                        )
                    }
                    Text(
                        text = formattedTime.split(",").get(0),
                        modifier = Modifier.padding(horizontal = 2.dp),
                        fontFamily = customFontFamily,
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp,
                        color = Color.Black.copy(alpha = 0.5f),
                        lineHeight = 18.sp
                    )
                }
            }
        }

        if (isMoreEnabled)
            IconButton(onClick = { onClick.invoke() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_h_dot),
                    contentDescription = "",
                    tint = Color.Black.copy(alpha = 0.5f)
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