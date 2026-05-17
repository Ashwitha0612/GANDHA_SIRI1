package com.example.gandhasiri.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gandhasiri.data.TreeRepository
import com.example.gandhasiri.ui.screens.*
import com.example.gandhasiri.viewmodel.TreeViewModel
import com.example.gandhasiri.viewmodel.TreeViewModelFactory

@Composable
fun NavGraph(repository: TreeRepository) {
    val navController = rememberNavController()
    val treeViewModel: TreeViewModel = viewModel(factory = TreeViewModelFactory(repository))

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(onSplashFinished = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }

        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = treeViewModel,
                onRegisterTree = { navController.navigate(Screen.RegisterTree.route) },
                onViewTrees   = { navController.navigate(Screen.TreeList.route) },
                onLegalGuide  = { navController.navigate(Screen.LegalGuide.route) },
                onSecurityAlert = { navController.navigate(Screen.SecurityAlert.route) }
            )
        }

        composable(Screen.RegisterTree.route) {
            RegisterTreeScreen(
                viewModel = treeViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.TreeList.route) {
            TreeListScreen(
                viewModel = treeViewModel,
                onTreeClick = { id -> navController.navigate(Screen.TreeDetail.createRoute(id)) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.TreeDetail.route,
            arguments = listOf(navArgument("treeId") { type = NavType.IntType })
        ) { backStack ->
            val treeId = backStack.arguments?.getInt("treeId") ?: return@composable
            TreeDetailScreen(
                treeId = treeId,
                viewModel = treeViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.LegalGuide.route) {
            LegalGuideScreen(onBack = { navController.popBackStack() })
        }

        composable(Screen.SecurityAlert.route) {
            SecurityAlertScreen(onBack = { navController.popBackStack() })
        }
    }
}
