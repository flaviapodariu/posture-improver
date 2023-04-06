package com.licenta.postureimprover.screens.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.licenta.postureimprover.screens.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Navigation(navController: NavHostController, nickname: String) {

    println("y nickname is $nickname")

    NavHost(
        navController = navController,
        startDestination = Routes.Start.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = Routes.Start.route) {
            if(nickname != "") {
                navController.popBackStack()
                navController.navigate(Routes.Dashboard.passArgs(nickname))
            }
            else {
                navController.popBackStack()
                navController.navigate(Routes.Login.route)
            }
        }
        composable(route= Routes.Login.route) {
            LoginScreen(
                goToDashboard = {
                    navController.navigate(Routes.Dashboard.passArgs(it))
            },
                goToSignUp = {
                    navController.popBackStack()
                    navController.navigate(Routes.SignUp.route)
                }
            )
        }
        composable(route= Routes.SignUp.route) {
            SignUpScreen(
                goToDashboard = {
                    navController.navigate(Routes.Dashboard.passArgs(it))
                },
                goToLogin = {
                    navController.popBackStack()
                    navController.navigate(Routes.Login.route)
                }
            )
        }
        composable(
            route = Routes.Dashboard.route + "/{nickname}",
            arguments = listOf(
                navArgument("nickname"){
                    type = NavType.StringType
                    nullable = true
                }
            )

        ){
            entry ->
            entry.arguments?.getString("nickname")?.let { DashboardScreen(nickname = it) }
        }

        composable(route= Routes.Camera.route) {
            CameraScreen(
                reopenCamera = {
                    navController.popBackStack()
                    navController.navigate(Routes.Camera.route)
                },
                goToWorkouts = {
                    navController.popBackStack()
                    navController.navigate(Routes.Workouts.route)
                }
            )
        }

        composable(route= Routes.Settings.route) {
            SettingsScreen(goToLogin = {
                navController.navigate(Routes.Login.route)
            })
        }

        composable(route= Routes.Workouts.route) {
            WorkoutScreen(goToExerciseDetail = {
                navController.navigate(Routes.ExerciseDetails.passArgs(it))
            })
        }
        composable(
            route= Routes.ExerciseDetails.route + "/{exerciseId}",
            arguments = listOf(
                navArgument("exerciseId"){
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { entry ->
            entry.arguments?.getString("exerciseId")?.let { ExerciseDetailsScreen() }
        }

    }
    
}

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Dashboard : Routes("dashboard")
    object SignUp : Routes( "signup")
    object Camera : Routes("camera")
    object Settings: Routes("settings")
    object Start: Routes("start")
    object Workouts: Routes("workouts")
    object ExerciseDetails: Routes("exercise")

    fun passArgs(vararg args: String) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }

    }
}
