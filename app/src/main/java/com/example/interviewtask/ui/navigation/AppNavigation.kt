package com.example.interviewtask.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import androidx.navigation.navArgument
import com.example.interviewtask.ui.screens.DetailScreen
import com.example.interviewtask.ui.screens.FeedScreen
import com.example.interviewtask.ui.screens.GroupScreen
import com.example.interviewtask.ui.screens.HomeScreen
import com.example.interviewtask.ui.screens.MoreScreen
import com.example.interviewtask.ui.screens.NotificationScreen
import com.example.interviewtask.ui.viewmodels.DetailViewModel
import com.example.interviewtask.ui.viewmodels.FeedsViewModel

@Composable
fun AppNavigation(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(modifier = Modifier.padding(innerPadding),
        navController = navController,
        graph = navController.createGraph(
            startDestination = Screens.Feeds.route
        ) {
            composable(Screens.Home.route) { HomeScreen() }
            composable(Screens.Feeds.route) {
                FeedScreen(
                    hiltViewModel<FeedsViewModel>(),
                    navController
                )
            }
            composable(Screens.Groups.route) {
                println("called$$ items groupscren")
                GroupScreen() }
            composable(Screens.Notifications.route) { NotificationScreen() }
            composable(Screens.More.route) { MoreScreen() }
            composable(Screens.Detail.route, arguments = listOf(navArgument("posterId") {
                type = NavType.StringType
            },),  enterTransition = {
               slideIntoContainer(
                    animationSpec = tween(500, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
                exitTransition = {
                    slideOutOfContainer(
                        animationSpec = tween(500, easing = EaseOut),
                        towards = AnimatedContentTransitionScope.SlideDirection.End
                    )
                }) { backStackEntry ->
                val postId = backStackEntry.arguments?.getString("posterId")!!

                DetailScreen(hiltViewModel<DetailViewModel>().apply {
                    selectedId =postId
                })

            }
        })
}