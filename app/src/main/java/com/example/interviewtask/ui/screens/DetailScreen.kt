package com.example.interviewtask.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.interviewtask.data.models.Stream
import com.example.interviewtask.ui.composables.CommentsCard
import com.example.interviewtask.ui.composables.DetailTopBar
import com.example.interviewtask.ui.composables.FeedItemContent
import com.example.interviewtask.ui.composables.Loader
import com.example.interviewtask.ui.composables.MoreBottomSheet
import com.example.interviewtask.ui.composables.TitleSection
import com.example.interviewtask.ui.utils.UiState
import com.example.interviewtask.ui.utils.getDummyData
import com.example.interviewtask.ui.utils.getStream
import com.example.interviewtask.ui.utils.showToast
import com.example.interviewtask.ui.viewmodels.DetailViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(detailViewModel: DetailViewModel, navController: NavController) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
//        detailViewModel.getSelectedFeed()
        detailViewModel.navigationEvent.collectLatest {
            navController.popBackStack()
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {

        Column {
            DetailTopBar() {
                detailViewModel.onBackClicked()
            }
            detailViewModel.selectedFeedStateFlow.collectAsState().value.let { it: UiState<Stream> ->
                when (it) {
                    is UiState.Failure -> {
                        LaunchedEffect(it) {
                            context.showToast(it.error)
                        }
                    }

                    is UiState.Loading -> Loader()
                    is UiState.Success -> {
                        LazyColumn {
                            item {
                                FeedItemContent(stream = it.data, false)
                                HorizontalDivider(
                                    thickness = 0.5.dp,
                                    color = MaterialTheme.colorScheme.outline
                                )
                                TitleSection(
                                    it.data.comments.size.toString()
                                )
                                HorizontalDivider(
                                    thickness = 0.5.dp,
                                    color = MaterialTheme.colorScheme.outline
                                )
                            }
                            items(it.data.comments) { it ->
                                CommentsCard(it) {
                                    detailViewModel.showMore()
                                }
                            }
                        }
                    }
                }
            }
        }
        MoreBottomSheet(
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            detailViewModel
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Surface(modifier = Modifier.fillMaxSize()) {

        Column {
            DetailTopBar() {
//                detailViewModel.onBackClicked()
            }
           UiState.Success(getStream(context)).let { it: UiState<Stream> ->
                when (it) {
                    is UiState.Failure -> {
                        LaunchedEffect(it) {
                            context.showToast(it.error)
                        }
                    }

                    is UiState.Loading -> Loader()
                    is UiState.Success -> {
                        LazyColumn {
                            item {
                                FeedItemContent(stream = it.data, false)
                                HorizontalDivider(
                                    thickness = 0.5.dp,
                                    color = MaterialTheme.colorScheme.outline
                                )
                                TitleSection(
                                    it.data.comments.size.toString()
                                )
                                HorizontalDivider(
                                    thickness = 0.5.dp,
                                    color = MaterialTheme.colorScheme.outline
                                )
                            }
                            items(it.data.comments) { it ->
                                CommentsCard(it) {
//                                    detailViewModel.showMore()
                                }
                            }
                        }
                    }
                }
            }
        }
      /*  MoreBottomSheet(
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            detailViewModel
        )*/
    }

}


/*@Composable
fun CommentSection(stream: Stream, onClick: () -> Unit) {
    LazyColumn {
        items(stream.comments) { it ->
            CommentsCard(it, onClick)
        }
    }
}*/







