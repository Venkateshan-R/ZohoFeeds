package com.example.interviewtask.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interviewtask.R
import com.example.interviewtask.ui.theme.ExtraLightGrey
import com.example.interviewtask.ui.theme.customFontFamily

@Composable
fun FeedsBottomSection(viewCount: String) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_like),
                    contentDescription = "",
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_comment),
                    contentDescription = "",
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.padding(5.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_views), tint = ExtraLightGrey,
                    contentDescription = "",
                )
                Text(
                    text = viewCount,
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp,
                    color = ExtraLightGrey
                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewFeedsBottomScreen(modifier: Modifier = Modifier) {
//    FeedsBottomSection()

}