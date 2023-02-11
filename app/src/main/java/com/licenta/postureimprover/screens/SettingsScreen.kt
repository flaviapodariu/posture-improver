package com.licenta.postureimprover.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.licenta.postureimprover.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {

        ListItem(
            modifier = Modifier.fillMaxWidth(),
            headlineText = { Text(text = "Log Out") },
            trailingContent = {
                IconButton(
                    onClick = { navController.navigate(Routes.Login.route) }
                ){
                    Icon(
                        imageVector = Icons.Rounded.ExitToApp,
                        contentDescription = "exit"
                    )

                }
            }
        )

    }
}