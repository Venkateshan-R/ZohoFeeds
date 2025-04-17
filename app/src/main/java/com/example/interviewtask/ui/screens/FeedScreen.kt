package com.example.interviewtask.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.interviewtask.data.models.PostModel
import com.example.interviewtask.ui.activities.FeedsTopBar
import com.example.interviewtask.ui.composables.FeedsCard
import com.example.interviewtask.ui.composables.Loader
import com.example.interviewtask.ui.utils.UiState
import com.example.interviewtask.ui.utils.getDummyData
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
            println("sdfa " + it.toString())
            when (it) {
                is UiState.Loading -> {
                    Loader()
                }

                is UiState.Success -> {
                    FeedsSection(it.data, feedsViewModel)
                }

                is UiState.Failure -> {
                    context.showToast(it.error)
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
    PullToRefreshBox(
        isRefreshing = viewModel.isRefreshing.collectAsState().value, onRefresh = {
            viewModel.isRefreshing.value = true
            viewModel.refreshFeeds()
        }, modifier = Modifier
    ) {
        content()
    }
}


@Composable
fun ErrorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Data not found", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Swipe down to refresh", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun FeedsSection(postModel: PostModel, feedsViewModel: FeedsViewModel) {

    Box(
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
        Column {
            FeedsTopBar()
            LazyColumn(Modifier.fillMaxSize()) {
                items(postModel.recentStreams.streams) {
                    FeedsCard(it) {
                        feedsViewModel.feedsItemClicked(it)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFeedSectino(modifier: Modifier = Modifier) {

    FeedsSection(
        postModel = getDummyData(LocalContext.current)!!,
        feedsViewModel = hiltViewModel<FeedsViewModel>()
    )

}


