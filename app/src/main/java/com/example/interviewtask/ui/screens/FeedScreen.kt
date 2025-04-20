package com.example.interviewtask.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.interviewtask.data.models.FeedsModel
import com.example.interviewtask.ui.composables.ErrorScreen
import com.example.interviewtask.ui.composables.FeedsCard
import com.example.interviewtask.ui.composables.FeedsTopBar
import com.example.interviewtask.ui.composables.Loader
import com.example.interviewtask.ui.utils.UiState
import com.example.interviewtask.ui.utils.showToast
import com.example.interviewtask.ui.viewmodels.FeedsViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FeedScreen(feedsViewModel: FeedsViewModel, controller: NavController) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        feedsViewModel.navigationEvent.collectLatest { route ->
            controller.navigate(route)
        }
    }

    SwipeRefresh(viewModel = feedsViewModel) {
        feedsViewModel.feedsStateFlow.collectAsState().value.let {
            when (it) {
                is UiState.Loading -> {
                    Loader()
                }

                is UiState.Success -> {
                    FeedsMainScreen(it.data, feedsViewModel)
                }

                is UiState.Failure -> {
                    LaunchedEffect(it) {
                        context.showToast(it.error)
                    }
                    ErrorScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeRefresh(
    viewModel: FeedsViewModel, content: @Composable () -> Unit
) {
    val rememberPUlledState = rememberPullToRefreshState()
    PullToRefreshBox(indicator = {
        Indicator(
            modifier = Modifier.align(Alignment.TopCenter),
            isRefreshing = viewModel.isRefreshing.collectAsState().value,
            state =rememberPUlledState, containerColor = MaterialTheme.colorScheme.surface,
            color = MaterialTheme.colorScheme.primary
        )
    },
        isRefreshing = viewModel.isRefreshing.collectAsState().value, onRefresh = {
            viewModel.refreshFeeds()
        }, modifier = Modifier, state = rememberPUlledState
    ) {
        content()
    }
}


@Composable
fun FeedsMainScreen(feedsModel: FeedsModel, feedsViewModel: FeedsViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFe5e3f4),
                        Color(0xFFEFECF7)
                    )
                )
            )
    ) {
        FeedsTopBar()
        LazyColumn(Modifier.fillMaxSize()) {
            items(feedsModel.recentStreams.streams) {
                FeedsCard(it) {
                    feedsViewModel.feedsItemClicked(it)
                }
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun PreviewFeedSectino(modifier: Modifier = Modifier) {
    FeedsSection(
        feedsModel = getDummyData(LocalContext.current)!!,
        feedsViewModel = hiltViewModel<FeedsViewModel>()
    )

}*/


