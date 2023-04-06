package com.licenta.postureimprover.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.licenta.postureimprover.screens.viewmodels.AuthenticationViewModel
import com.licenta.postureimprover.screens.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    goToLogin: () -> Unit = {},
    authViewModel: AuthenticationViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel()
    ) {
    Column(modifier = Modifier.fillMaxSize()) {

        ListItem(
            modifier = Modifier.fillMaxWidth(),
            headlineText = { Text(text = "Log Out") },
            trailingContent = {
                IconButton(
                    onClick = { authViewModel.logout().also{
                        goToLogin()
                    } }
                ){
                    Icon(
                        imageVector = Icons.Rounded.ExitToApp,
                        contentDescription = "exit"
                    )

                }
            }
        )

        ListItem(
            modifier = Modifier.fillMaxWidth(),
            headlineText = { Text(text= "Dark Mode") },
            trailingContent = {
                Switch(
                    checked = mainViewModel.darkTheme,
                    onCheckedChange = {
                        mainViewModel.darkTheme = !mainViewModel.darkTheme
                    },
                    enabled = true
                )
            }
        )

    }
}
