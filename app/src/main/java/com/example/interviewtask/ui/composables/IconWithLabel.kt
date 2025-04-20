package com.example.interviewtask.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.interviewtask.R

@Composable
fun IconWithLabel(iconId: Int, labelText: String) {
    Row(
        modifier = Modifier.padding(18.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "",
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = labelText,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewIconWithLabel(){
    IconWithLabel(iconId = R.drawable.ic_copy, labelText ="Copy URL" )
}
