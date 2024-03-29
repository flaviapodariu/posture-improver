package com.licenta.postureimprover.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.licenta.postureimprover.screens.viewmodels.AuthenticationViewModel
import com.licenta.postureimprover.screens.viewmodels.AuthenticationViewModel.Companion.USER_ID
import com.licenta.postureimprover.screens.viewmodels.MainViewModel

@Composable
fun SettingsScreen(
    goToLogin: () -> Unit = {},
    goToSignUp: () -> Unit = {},
    authViewModel: AuthenticationViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {

        ListItem(
            modifier = Modifier.fillMaxWidth(),
            headlineContent = { Text(text = "Log Out") },
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

        if(USER_ID == 0) {
            ListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        goToSignUp()
                    },

                headlineContent = { Text(text = "Create account") },
                trailingContent = {
                    Icon(
                        imageVector = Icons.Rounded.AccountBox,
                        contentDescription = "create account"
                    )
                }
            )
        }



    }
}