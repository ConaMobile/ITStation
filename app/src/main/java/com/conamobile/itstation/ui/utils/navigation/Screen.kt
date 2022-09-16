package com.conamobile.itstation.ui.utils.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object MainScreen : Screen("main_screen")
}
