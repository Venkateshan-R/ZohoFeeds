package com.example.interviewtask.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun FeatureInProgress() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        Text("Feature in Progress...")
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewFeatureInProgress() {
    FeatureInProgress()
}
