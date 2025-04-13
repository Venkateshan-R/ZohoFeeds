package com.example.interviewtask.ui.composables

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipsSection(tags: List<String>) {
    FlowRow(overflow = FlowRowOverflow.Clip) {
        tags.forEach {
            SuggestionChip(
                modifier = Modifier.padding(horizontal = 5.dp),
                onClick = { },
                label = { Text(text = it) },
                shape = RoundedCornerShape(percent = 50)
            )
        }
    }
}
