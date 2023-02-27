package com.licenta.postureimprover.screens.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.licenta.postureimprover.screens.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardScaffold(
    navController: NavHostController,
    icons: List<BottomNavIcon> = listOf(
        BottomNavIcon(
            route = Routes.Dashboard.passArgs("anonymous for now"),
            icon = Icons.Rounded.Home,
            description = "Home"
        ),
        BottomNavIcon(
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

    Scaffold(
            floatingActionButton = {
                if (showBottomBar) {
                    FloatingActionButton(onClick = {
                        navController.navigate(Routes.Camera.route)  //maybe use param function
                    }) {
                        Icon(Icons.Rounded.Add, "Camera")
                    }
                }
            },

        bottomBar = {
            if(showBottomBar) {
                NavigationBar{
                    icons.forEachIndexed { index, icon ->
                        NavigationBarItem(
                            icon = { Icon(icon.icon, contentDescription = icon.description) },
                            label = { Text(icon.description) },
                            selected = selectedIcon == index,
                            onClick = {
                                selectedIcon = index
                                navController.navigate(icon.route)
                            }
                        )
                    }
                }
            }
        },
    ) {
        content(it)
    }
}
















