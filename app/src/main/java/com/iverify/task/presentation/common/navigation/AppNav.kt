package com.iverify.task.presentation.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iverify.task.presentation.devices.DeviceListScreen
import com.iverify.task.presentation.signin.SignInScreen
import com.iverify.task.presentation.splash.SplashScreen

@Composable
fun AppNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.SignIn.route) {
            SignInScreen(navController = navController)
        }
        composable(Screen.DeviceList.route) {
            DeviceListScreen()
        }
    }
}