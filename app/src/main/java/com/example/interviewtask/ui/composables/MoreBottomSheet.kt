package com.example.interviewtask.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.interviewtask.R
import com.example.interviewtask.ui.viewmodels.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreBottomSheet(
    sheetState: SheetState, detailViewModel: DetailViewModel
) {
    val isBottomSheetVisible = detailViewModel.moreEvent.collectAsState(false).value
    if (isBottomSheetVisible) ModalBottomSheet(
        onDismissRequest = { detailViewModel.hideMore() },
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            IconWithLabel(R.drawable.ic_link, stringResource(R.string.copy_url))
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outline.copy(0.05f),
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            IconWithLabel(R.drawable.ic_copy, stringResource(R.string.copy_this_comment))
        }
    }
}

