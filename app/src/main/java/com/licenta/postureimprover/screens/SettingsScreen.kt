package com.licenta.postureimprover.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavHostController
import com.licenta.postureimprover.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {

        ListItem(
          headlineText = { Text(text = "Log Out") }
        )

        ClickableText(
            text = AnnotatedString(text="Log Out"),
            onClick = {
                navController.navigate(Routes.Login.route)
            }
        )

    }
}