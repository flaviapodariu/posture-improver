package com.licenta.postureimprover.screens.components

import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.licenta.postureimprover.screens.navigation.Routes
import com.licenta.postureimprover.theme.Camera
import com.licenta.postureimprover.theme.Workouts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardScaffold(
    navController: NavHostController,
    prefs: SharedPreferences,
    icons: List<BottomNavIcon> = listOf(
        BottomNavIcon(
            index = 0,
            route = Routes.Dashboard.passArgs(prefs.getString("nickname", "")!!),
            icon = Icons.Rounded.Home,
            description = "Home"
        ),
        BottomNavIcon(
            index = 1,
            route = Routes.Workouts.route,
            icon = Icons.Workouts,
            description = "Workouts"
        ),
        BottomNavIcon(
            index = 2,
            route = Routes.Settings.route,
            icon = Icons.Rounded.Settings,
            description = "Settings"
        )

    //TODO add bottom nav icons here

    ),
    showBottomBar: Boolean = true,
    content: @Composable (PaddingValues) -> Unit,
) {

    var selectedIcon by remember{ mutableStateOf(0) }
    var showTimerInfo by remember { mutableStateOf(false) }


    if(showTimerInfo) {
        FullTimerDialog(
            goToCameraScreen = { navController.navigate(Routes.Camera.route) },
            dialogStillShowing = { stillShowing -> showTimerInfo = stillShowing }
        )
    }

    Scaffold(
            floatingActionButton = {
                if (showBottomBar) {
                    FloatingActionButton(onClick = {
                        showTimerInfo = true

                    }) {
                        Icon(Icons.Camera, "Camera")
                    }
                }
            },

        bottomBar = {
            if(showBottomBar) {
                NavigationBar{
                    val curr_route = navController.currentDestination?.route
                    println(curr_route)
                    icons.forEachIndexed { index, icon ->
                        NavigationBarItem(
                            icon = { Icon(icon.icon, contentDescription = icon.description, tint = MaterialTheme.colorScheme.secondary) },
                            label = { Text(icon.description) },
                            selected = curr_route == icon.route,
                            onClick = {
                                navController.navigate(icon.route)
                            },
                        )
                    }
                }
            }
        },
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            content(it)
        }
    }
}


data class BottomNavIcon(
    val index: Int,
    val route: String,
    val icon: ImageVector,
    val description: String
)







