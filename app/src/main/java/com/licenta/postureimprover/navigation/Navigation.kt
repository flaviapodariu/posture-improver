package com.licenta.postureimprover.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.licenta.postureimprover.screens.CameraScreen
import com.licenta.postureimprover.screens.DashboardScreen
import com.licenta.postureimprover.screens.LoginScreen
import com.licenta.postureimprover.screens.SignUpScreen

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Navigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        composable(route=Routes.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route=Routes.SignUp.route){
            SignUpScreen(navController =  navController)
        }
        composable(
            route=Routes.Dashboard.route + "/{email}",
            arguments = listOf(
                navArgument("email"){
                    type = NavType.StringType
                    nullable = true
                }
            )

        ){
            entry ->
            entry.arguments?.getString("email")?.let { DashboardScreen(navController , email = it) }
        }

        composable(route=Routes.Camera.route){
            CameraScreen()
        }

    }
    
}

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Dashboard : Routes("dashboard")
    object SignUp : Routes( "signup")
    object Camera : Routes("camera")

    fun passArgs(vararg args: String) : String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }

    }
}
