package com.example.footpassion.ui.theme

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.footpassion.ui.theme.screens.AddGameScreen
import com.example.footpassion.ui.theme.screens.ListMatchScreen
import com.example.footpassion.ui.theme.screens.ResultListScreen
import com.example.footpassion.viewmodel.MainViewModel

//sealed permet de dire qu'une classe est héritable (ici par SearchScreen et DetailScreen)
//Uniquement par les sous classes qu'elle contient
sealed class Routes(val route: String) {
    //Route 1
    data object AddGameScreen : Routes("AddGameScreen")

    //Route 2
    data object ListMatchScreen : Routes("ListMatchScreen")

    //Route 3
    data object ResultListScreen : Routes("ResultListScreen")
}

@Composable
fun AppNavigation() {

    val navHostController : NavHostController = rememberNavController()

    //viewModel appartient au framework permet de récupérer une instance déjà existante s'il en existe une
    val mainViewModel: MainViewModel = viewModel()



    //Import version avec Composable
    NavHost(navController = navHostController, startDestination = Routes.AddGameScreen.route) {
        //Route 1 vers notre AddGameScreen
        composable(Routes.AddGameScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            AddGameScreen(navHostController, mainViewModel = mainViewModel)
        }

        //Route 2 vers un écran de détail
        composable(Routes.ListMatchScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            ListMatchScreen(navHostController, mainViewModel = mainViewModel)
        }


        composable(Routes.ResultListScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            ResultListScreen(navHostController, mainViewModel = mainViewModel)
        }

    }
}