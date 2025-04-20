package com.example.interviewtask.ui.composables


import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.interviewtask.ui.navigation.Screens

@Composable
fun CustomBottomAppBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val selectedItemIndex = remember {
        mutableStateOf(1)
    }

    val bottomBarItemsList = remember {
        listOf<Screens>(
            Screens.Home, Screens.Feeds, Screens.Groups, Screens.Notifications, Screens.More
        )
    }
    if (currentRoute != Screens.Detail.route) {
        BottomAppBar(containerColor = MaterialTheme.colorScheme.background) {
            bottomBarItemsList.forEachIndexed { index, bottomBarItem ->
                NavigationBarItem(label = {
                    Row {
                        Text(
                            text = bottomBarItem.title,
                            maxLines = 1,
                            overflow = TextOverflow.Visible,
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                }, selected = currentRoute == bottomBarItem.route, onClick = {

                    selectedItemIndex.value = index
                    navController.navigate(bottomBarItem.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                }, icon = {
                    val icon =
                        if (currentRoute == bottomBarItem.route) bottomBarItem.selectedIconId else bottomBarItem.defaultIconId
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = "",
                    )

                    BadgedBox(
                        badge = {
                            if (bottomBarItem.route == Screens.Notifications.route)
                                Badge { Text("3") }
                        }
                    ) {
                        Icon(
                            painter = painterResource(icon),
                            contentDescription = "",
                        )
                    }

                }, colors = NavigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedIndicatorColor =
                    MaterialTheme.colorScheme.secondaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.onSurface,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledIconColor = MaterialTheme.colorScheme.onSurface,
                )
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCustomBottomBar() {
    CustomBottomAppBar(navController = rememberNavController())
}
