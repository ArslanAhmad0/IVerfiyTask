package com.iverify.task.presentation.common.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object SignIn : Screen("signin")
    data object DeviceList : Screen("deviceList")
}