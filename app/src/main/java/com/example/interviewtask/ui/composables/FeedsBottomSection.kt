package com.example.interviewtask.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.interviewtask.R

@Composable
fun FeedsBottomSection(viewCount : String) {

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.padding(5.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_like),
                    contentDescription = "",
                    modifier = Modifier.padding(5.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_comment),
                    contentDescription = "",
                    modifier = Modifier.padding(5.dp)
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_views),
                    contentDescription = "",
                    modifier = Modifier.padding(5.dp)
                )
                Text(text = viewCount)
            }

        }
    }
}

@Preview
@Composable
fun PreviewFeedsBottomScreen(modifier: Modifier = Modifier) {
//    FeedsBottomSection()

}