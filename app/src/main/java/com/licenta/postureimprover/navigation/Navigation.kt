package com.licenta.postureimprover.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.licenta.postureimprover.screens.CameraComponent
import com.licenta.postureimprover.screens.LoginComponent
import com.licenta.postureimprover.screens.SignUpComponent

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(route=Routes.Login.route) {
            LoginComponent(navController = navController)
        }
        composable(route=Routes.SignUp.route){
            SignUpComponent(navController =  navController)
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
            entry -> Dashboard(email = entry.arguments?.getString("email"))
        }

        composable(route=Routes.Camera.route){
            CameraComponent()
        }

    }
    
}

@Composable
fun Dashboard(email: String?) {
    Text(text = "hello, $email")
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
