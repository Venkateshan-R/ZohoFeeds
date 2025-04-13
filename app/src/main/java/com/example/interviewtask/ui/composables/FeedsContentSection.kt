package com.example.interviewtask.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FeedsContentSection(content : String) {

    Column {
        Text(
            text = content,
            modifier = Modifier.padding(4.dp)
        )
    }
}