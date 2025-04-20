package com.example.interviewtask.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.interviewtask.ui.composables.CustomBottomAppBar
import com.example.interviewtask.ui.composables.CustomFAB
import com.example.interviewtask.ui.navigation.AppNavigation
import com.example.interviewtask.ui.theme.InterviewTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewTaskTheme {
//                Box(modifier = Modifier.background(color = Color.Red)) {
                AppMain()
//                }
            }
        }
    }
}

@Composable
fun AppMain() {
    val navController = rememberNavController()

    Scaffold(contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        containerColor = Color.Transparent,
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            CustomBottomAppBar(navController)
        },
        floatingActionButton = {
            CustomFAB(navController = navController)

        }) { innerPadding ->
        AppNavigation(innerPadding, navController)
    }
}





@Preview
@Composable
fun PreviewMain(modifier: Modifier = Modifier) {
    MaterialTheme {
        AppMain()
    }
}



