package com.example.interviewtask.ui.navigation

import com.example.interviewtask.R

sealed class Screens(
    val route: String,
    val title: String,
    val defaultIconId: Int,
    val selectedIconId: Int
) {

    object Home : Screens("Home", "Home", R.drawable.ic_home, R.drawable.ic_home_fill)
    object Feeds : Screens("Feeds", "Feeds", R.drawable.ic_feeds, R.drawable.ic_feeds_fill)
    object Groups : Screens("Groups", "Groups", R.drawable.ic_groups, R.drawable.ic_groups_fill)
    object Notifications : Screens(
        "Notifications",
        "Notifications",
        R.drawable.ic_notifications,
        R.drawable.ic_notifications_fill
    )

    object More : Screens("More", "More", R.drawable.ic_bottom_more, R.drawable.ic_bottom_more_fill)
    object Detail : Screens("Detail/{posterId}", "Detail", 0, 0) {
        fun withArgs(posterId: String) = "Detail/$posterId"

    }


}
