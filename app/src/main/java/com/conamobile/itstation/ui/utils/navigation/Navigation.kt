package com.conamobile.itstation.ui.utils.navigation

import android.app.Activity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.conamobile.itstation.ui.login.LoginScreen
import com.conamobile.itstation.ui.main.MainScreen
import com.conamobile.itstation.ui.splash.SplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@ExperimentalAnimationApi
@Composable
fun Navigation(activity: Activity) {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screen.MainScreen.route) {
        composable(
            route = Screen.SplashScreen.route,
        ) {
            SplashScreen(navController = navController, activity)
        }
        composable(
            route = Screen.LoginScreen.route,
        ) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
    }
}