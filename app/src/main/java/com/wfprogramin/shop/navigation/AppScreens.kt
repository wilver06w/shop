package com.wfprogramin.shop.navigation

sealed class AppScreens(val route: String) {
    data object SplashScreen: AppScreens("Splash")
    data object LoginScreen: AppScreens("Login")
}