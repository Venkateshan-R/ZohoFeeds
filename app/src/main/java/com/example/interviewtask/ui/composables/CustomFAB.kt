package com.example.interviewtask.ui.composables


import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.interviewtask.R
import com.example.interviewtask.ui.navigation.Screens

@Composable
fun CustomFAB(navController: NavController) {
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
}

