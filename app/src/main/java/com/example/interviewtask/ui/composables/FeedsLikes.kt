package com.example.interviewtask.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interviewtask.R
import com.example.interviewtask.ui.theme.ExtraLightGrey
import com.example.interviewtask.ui.theme.customFontFamily

@Composable
fun FeedsLikesAndComments(
    likesCount: String,
    commentsCount: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_liked),
            modifier = Modifier.size(24.dp),
            contentDescription = ""
        )
        Text(
            text = likesCount,
            modifier = Modifier.padding(horizontal = 2.dp, vertical = 1.dp),
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            color = ExtraLightGrey
        )
        Text(
            text = "•",
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp),
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            color = ExtraLightGrey
        )
        Text(
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            color = ExtraLightGrey,
            text = "$commentsCount Comments",
        )
    }
}