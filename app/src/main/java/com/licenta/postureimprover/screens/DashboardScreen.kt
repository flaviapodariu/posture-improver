package com.licenta.postureimprover.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun DashboardScreen(navController: NavHostController, email:String) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Hello, $email!")
    }

}












