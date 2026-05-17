package com.example.gandhasiri.ui.navigation

sealed class Screen(val route: String) {
    object Splash      : Screen("splash")
    object Home        : Screen("home")
    object RegisterTree: Screen("register_tree")
    object TreeList    : Screen("tree_list")
    object LegalGuide  : Screen("legal_guide")
    object SecurityAlert: Screen("security_alert")

    object TreeDetail  : Screen("tree_detail/{treeId}") {
        fun createRoute(treeId: Int) = "tree_detail/$treeId"
    }
}
