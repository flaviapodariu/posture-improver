package com.licenta.postureimprover.screens.components

import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.licenta.postureimprover.screens.navigation.Routes
import com.licenta.postureimprover.screens.viewmodels.TimerDialogViewModel
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardScaffold(
    navController: NavHostController,
    prefs: SharedPreferences,
    icons: List<BottomNavIcon> = listOf(
        BottomNavIcon(
            route = Routes.Dashboard.passArgs(prefs.getString("nickname", "")!!),
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










