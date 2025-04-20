package com.example.interviewtask.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.interviewtask.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(onBackClick: () -> Unit) {
    Surface {
        TopAppBar(title = {
            Text(
                text = stringResource(R.string.post),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 6.dp),
                color = MaterialTheme.colorScheme.onSurface,
            )
        }, actions = {
            TopBarIcon(iconResource = R.drawable.ic_appbar_more) {

            }
        }, navigationIcon = {
            TopBarIcon(iconResource = R.drawable.ic_back, onClick = onBackClick)
        })
    }
}