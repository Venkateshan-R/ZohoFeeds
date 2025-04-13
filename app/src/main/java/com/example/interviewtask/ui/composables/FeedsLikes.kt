package com.example.interviewtask.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.interviewtask.R

@Composable
fun FeedsLikesAndComments(
    likesCount: String,
    commentsCount: String,
    modifier: Modifier = Modifier
) {

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
        Image(painter = painterResource(id = R.drawable.ic_liked), contentDescription = "")
        Text(text = likesCount, modifier = Modifier.padding(horizontal = 2.dp, vertical = 1.dp))
        Text(text = "•", modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp))
        Text(
            text = "$commentsCount Comments",
            modifier = Modifier.padding(horizontal = 2.dp, vertical = 1.dp)
        )
    }

}