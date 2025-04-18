package com.example.interviewtask.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.interviewtask.R
import com.example.interviewtask.ui.navigation.AppNavigation
import com.example.interviewtask.ui.navigation.Screens
import com.example.interviewtask.ui.screens.FeedScreen
import com.example.interviewtask.ui.theme.ActoinIconButtonColor
import com.example.interviewtask.ui.theme.InterviewTaskTheme
import com.example.interviewtask.ui.theme.customFontFamily
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewTaskTheme {
//                Box(modifier = Modifier.background(color = Color.Red)) {
                Main()
//                }
            }
        }
    }
}

@Composable
fun Main() {
    val navController = rememberNavController()

    Scaffold(contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        containerColor = Color.Transparent,
        contentColor = Color.Black,
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route
            if (currentRoute != Screens.Detail.route)
                CustomBottomAppBar(navController)
        },
        floatingActionButton = {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            if (currentRoute != Screens.Detail.route) {
                FloatingActionButton(
                    containerColor = MaterialTheme.colorScheme.primary,
                    onClick = { },
                    shape = CircleShape
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_status),
                        contentDescription = ""
                    )
                }
            }
        }) { innerPadding ->
        AppNavigation(innerPadding, navController)
    }
}

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

    BottomAppBar(containerColor = Color.White) {
        bottomBarItemsList.forEachIndexed { index, bottomBarItem ->
            NavigationBarItem(label = {
                Row {
                    Text(
                        text = bottomBarItem.title, maxLines = 1, overflow = TextOverflow.Visible,
                        style = if(currentRoute == bottomBarItem.route)  MaterialTheme.typography.labelMedium
                        else  MaterialTheme.typography.labelSmall
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
                unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledIconColor = MaterialTheme.colorScheme.onSurface,
            )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedsTopBar() {

    TopAppBar(
        colors = TopAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor =  Color.Transparent,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.primary
        ), title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 6.dp)
            ) {
                Text(
                    text = "Feeds", style = MaterialTheme.typography.titleLarge
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "",
                )
            }
        }, actions = {
            TopBarIcon(iconResource = R.drawable.ic_search) {

            }
            TopBarIcon(iconResource = R.drawable.ic_appbar_more) {

            }
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        }, navigationIcon = {
            TopBarIcon(iconResource = R.drawable.ic_menu) {

            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(onBackClick: () -> Unit) {
    Surface {
        TopAppBar(title = {
            Text(
                text = "Post",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 6.dp)
            )
        }, actions = {
            TopBarIcon(iconResource = R.drawable.ic_appbar_more) {

            }
        }, navigationIcon = {
            TopBarIcon(iconResource = R.drawable.ic_back, onClick = onBackClick)
        })
    }
}

@Preview
@Composable
fun PreviewMain(modifier: Modifier = Modifier) {
    MaterialTheme {
        Main()
    }
}

@Composable
fun TopBarIcon(iconResource: Int, onClick: () -> Unit) {
    FilledIconButton(
        onClick = onClick, colors = IconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = ActoinIconButtonColor,
            disabledContentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = ActoinIconButtonColor
        )
    ) {
        Icon(painter = painterResource(iconResource), contentDescription = "")
    }
}

