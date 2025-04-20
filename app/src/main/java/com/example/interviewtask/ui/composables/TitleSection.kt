package com.example.interviewtask.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.interviewtask.R
import com.example.interviewtask.ui.theme.customFontFamily

@Composable
fun TitleSection(commentsCount: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.comments, commentsCount.toInt()),
                fontFamily = customFontFamily,
                style = MaterialTheme.typography.titleSmall
            )
            Icon(
                modifier = Modifier.rotate(90f),
                painter = painterResource(id = R.drawable.ic_sort_arrow),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
fun PreviewTitleSection(){
    TitleSection(commentsCount = "5")
}