package com.example.interviewtask.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interviewtask.ui.theme.customFontFamily

@Composable
fun FeedsContentSection(content: String) {
    Text(
        text = content, style = MaterialTheme.typography.bodyLarge
        , color = MaterialTheme.colorScheme.onSurface
    )
}