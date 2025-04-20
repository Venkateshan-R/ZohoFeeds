package com.example.interviewtask.ui.composables

import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.interviewtask.R

@Composable
fun TopBarIcon(iconResource: Int, onClick: () -> Unit) {
    FilledIconButton(
        onClick = onClick, colors = IconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.secondary,
            disabledContentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Icon(painter = painterResource(iconResource), contentDescription = "")
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewTopBarIcon() {
    TopBarIcon(iconResource = R.drawable.ic_search) {}
}