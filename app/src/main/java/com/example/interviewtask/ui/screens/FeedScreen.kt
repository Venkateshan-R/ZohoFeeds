package com.example.interviewtask.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.interviewtask.ui.composables.FeedsCard
import com.example.interviewtask.ui.viewmodels.FeedsViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FeedScreen(feedsViewModel: FeedsViewModel, controller: NavController) {
    LaunchedEffect(key1 = true) {
        feedsViewModel.navigationEvent.collectLatest {route->
            controller.navigate(route)
        }

    }

    feedsViewModel.postStateFlow.collectAsState().value?.let {
        LazyColumn(Modifier.fillMaxSize()) {
            items(it.recentStreams.streams) {
                FeedsCard(it) {
                    feedsViewModel.feedsItemClicked(it)
                }
            }
        }
    }

}

