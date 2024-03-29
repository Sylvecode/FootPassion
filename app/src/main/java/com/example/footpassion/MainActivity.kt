package com.example.footpassion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.footpassion.ui.theme.AppNavigation
import com.example.footpassion.ui.theme.FootPassionTheme
import com.example.footpassion.ui.theme.screens.AddGameScreen
import com.example.footpassion.ui.theme.screens.ListMatchScreen
import com.example.footpassion.ui.theme.screens.ResultListScreen
import com.example.footpassion.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FootPassionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val mainViewModel: MainViewModel = viewModel()
                    val navHostController : NavHostController = rememberNavController()
                    //AddGameScreen(navHostController, mainViewModel = mainViewModel)
                    //ListMatchScreen(navHostController, mainViewModel = mainViewModel)
                    //ResultListScreen(navHostController, mainViewModel = mainViewModel)
                AppNavigation()
                }
            }
        }
    }
}

