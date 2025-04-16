package com.example.interviewtask.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.interviewtask.data.models.PostModel
import com.example.interviewtask.data.models.RecentStreams
import com.example.interviewtask.ui.activities.FeedsTopBar
import com.example.interviewtask.ui.composables.FeedsCard
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

    feedsViewModel.postStateFlow.collectAsState().value.let {
        when (it) {
            is UiState.Loading -> {
                Loader()
            }

            is UiState.Success -> {
                FeedsSection(it.data, feedsViewModel)
            }

            is UiState.Failure -> {
                context.showToast(it.error)
            }
        }
    }
}

@Composable
fun FeedsSection(postModel: PostModel, feedsViewModel: FeedsViewModel) {
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


