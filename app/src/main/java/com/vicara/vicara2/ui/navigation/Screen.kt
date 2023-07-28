package com.vicara.vicara2.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object AddKartu : Screen("addkartu")
    object KoleksiKartu : Screen("koleksikartu")
    object SusunKartu : Screen("susunkartu")
}
