package com.example.interviewtask.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.interviewtask.ui.theme.customFontFamily
import com.example.interviewtask.ui.utils.getStream

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipsSection(tags: List<String>) {
    if (tags.isNotEmpty())
        FlowRow(
            overflow = FlowRowOverflow.Clip,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            tags.forEach {
                Surface(
                    shape = RoundedCornerShape(percent = 50),
                    border = BorderStroke(
                        0.5.dp, color = MaterialTheme.colorScheme.outline,
                    ), color = MaterialTheme.colorScheme.surface
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        text = it,
                        fontFamily = customFontFamily,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
}

@Preview(showBackground = true)
@Composable
fun PreviewChipSection(modifier: Modifier = Modifier) {
    ChipsSection(tags = getStream(LocalContext.current).tags)
}