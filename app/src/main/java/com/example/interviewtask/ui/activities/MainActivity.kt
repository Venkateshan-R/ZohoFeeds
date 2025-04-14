package com.example.interviewtask.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.interviewtask.R
import com.example.interviewtask.ui.navigation.AppNavigation
import com.example.interviewtask.ui.navigation.Screens
import com.example.interviewtask.ui.screens.FeedScreen
import com.example.interviewtask.ui.theme.ActoinIconButtonColor
import com.example.interviewtask.ui.theme.InterviewTaskTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewTaskTheme {
                Surface {
                    Main()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {

    val navController = rememberNavController()/*   val navBackStackEntry by navController.currentBackStackEntryAsState()
       val currentRoute = navBackStackEntry?.destination?.route
   */
    Scaffold(containerColor = MaterialTheme.colorScheme.secondaryContainer, topBar = {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        if (currentRoute == Screens.Detail.route) DetailTopBar()
        else FeedsTopBar()

    }, modifier = Modifier.fillMaxSize(), bottomBar = {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        if (currentRoute != Screens.Detail.route) CustomBottomAppBar(navController)
    }, floatingActionButton = {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        if (currentRoute != Screens.Detail.route) {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = { },
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_status), contentDescription = ""
                )
            }
        }
    }) { innerPadding ->
        AppNavigation(innerPadding, navController)
    }
}

@Composable
fun CustomBottomAppBar(navController: NavController) {
    val selectedItemIndex = remember {
        mutableStateOf(1)
    }
    val bottomBarItemsList = remember {
        listOf<Screens>(
            Screens.Home, Screens.Feeds, Screens.Groups, Screens.Notifications, Screens.More
        )
    }


    BottomAppBar {
        bottomBarItemsList.forEachIndexed { index, bottomBarItem ->
            NavigationBarItem(label = {
                Row {
                    Text(text = bottomBarItem.title, maxLines = 1)
                }
            }, selected = selectedItemIndex.value == index, onClick = {
                selectedItemIndex.value = index
                navController.navigate(bottomBarItem.title)
            }, icon = {
                val icon =
                    if (selectedItemIndex.value == index) bottomBarItem.selectedIconId else bottomBarItem.defaultIconId
                Icon(
                    painter = painterResource(icon),
                    contentDescription = "",
                )
            }, colors = NavigationBarItemColors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedIndicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                selectedTextColor = MaterialTheme.colorScheme.onBackground,
                unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                unselectedTextColor = MaterialTheme.colorScheme.onBackground,
                disabledIconColor = MaterialTheme.colorScheme.onBackground,
                disabledTextColor = MaterialTheme.colorScheme.onBackground
            )
            )
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedsTopBar() {
    TopAppBar(colors = TopAppBarColors(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        scrolledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        navigationIconContentColor = MaterialTheme.colorScheme.primary,
        actionIconContentColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.onBackground
    ), title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Feeds")
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "")
        }
    }, actions = {
        TopBarIcon(iconResource = R.drawable.ic_search) {

        }
        TopBarIcon(iconResource = R.drawable.ic_appbar_more) {

        }
    }, navigationIcon = {
        TopBarIcon(iconResource = R.drawable.ic_menu) {

        }
    })

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar() {
    Surface {

        TopAppBar( title = {
            Text(text = "Post")

        }, actions = {
            TopBarIcon(iconResource = R.drawable.ic_appbar_more) {

            }
        }, navigationIcon = {
            TopBarIcon(iconResource = R.drawable.ic_back) {

            }
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

data class BottomBarItem(val title: String, val defaultIcon: Int, val selectedIcon: Int)
