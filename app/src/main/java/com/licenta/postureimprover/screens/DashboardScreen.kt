package com.licenta.postureimprover.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.licenta.postureimprover.data.api.dto.response.CaptureRes
import com.licenta.postureimprover.data.local.entities.CaptureEntity
import com.licenta.postureimprover.data.util.Task
import com.licenta.postureimprover.screens.viewmodels.DashboardViewModel

@Composable
fun DashboardScreen(
    nickname: String,
    dashboardViewModel: DashboardViewModel = hiltViewModel()
    ) {

    val context = LocalContext.current
    LaunchedEffect(key1 = dashboardViewModel.userCaptures , key2= context) {
       dashboardViewModel.getUserHistory()
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Hello, $nickname!")
        }

        if(dashboardViewModel.userCaptures.isNotEmpty()) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 15.dp)
            ) {
                CapturesList(captures = dashboardViewModel.userCaptures)

            }
        }
        else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)
            ) {
                Text(text = "Nothing here yet!")
            }
        }
    }

}

@Composable
fun CapturesList(captures: List<CaptureEntity>) {

    captures.forEach{
            Text(text =" ${ it.headForward }")

    }

}










