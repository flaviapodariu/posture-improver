package com.licenta.postureimprover.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.licenta.postureimprover.data.api.dto.CaptureRes
import com.licenta.postureimprover.data.util.AuthResponse
import com.licenta.postureimprover.domain.models.PostureCapture
import com.licenta.postureimprover.screens.viewmodels.DashboardViewModel

@Composable
fun DashboardScreen(
    nickname:String,
    dashboardViewModel: DashboardViewModel = hiltViewModel()
    ) {

    val context = LocalContext.current
    LaunchedEffect(key1 = dashboardViewModel.userHistoryState , key2= context) {
        dashboardViewModel.userHistoryState?.let {
            when(it) {
                is AuthResponse.Success -> {
                    dashboardViewModel.onUserCaptureChange(it.result.captures)
                }
                else -> Unit
            }
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Hello, $nickname!")
    }

    if(dashboardViewModel.userCaptures != null) {
        Column(modifier = Modifier.fillMaxSize()) {
            CapturesList(captures = dashboardViewModel.userCaptures!!)

        }
    }
    else {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Nothing here yet!")
        }
    }
}

@Composable
fun CapturesList(captures: List<CaptureRes>) {

    captures.forEach{
        Row {
            Text(text =" ${ it.headForward }")
        }

    }

}










