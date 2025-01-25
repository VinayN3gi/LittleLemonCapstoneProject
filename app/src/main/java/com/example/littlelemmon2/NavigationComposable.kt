package com.example.littlelemmon2

import android.app.AlertDialog
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController,
               builder: AlertDialog.Builder,
               sharedPreferences: SharedPreferences,
               databaseMenuItems: List<MenuItemRoom>) {
    val loginStatus = sharedPreferences.getBoolean("Login", false)
    val startDestination = if (loginStatus) { Home.route } else { Onboarding.route }

    NavHost(navController = navController, startDestination = startDestination)
    {
        composable(route = Onboarding.route) {
            OnBoarding(navController, builder, sharedPreferences = sharedPreferences)
        }
        composable(route = Home.route) {
            Home(navController, databaseMenuItems)
        }
        composable(route = Profile.route) {
            Profile(navController, sharedPreferences = sharedPreferences)
        }
    }
}
