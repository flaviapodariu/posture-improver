package com.licenta.postureimprover.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun CameraTimer(isTimerRunning: (Boolean) -> Unit) {
    var secondsLeft: Int by remember { mutableStateOf(2) }
    
    LaunchedEffect(key1 = secondsLeft) {

        if(secondsLeft > 0) {
            delay(1000)
            secondsLeft -= 1
        }
        else {
            isTimerRunning(false)
        }
    }
    
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = if(secondsLeft != 0) "$secondsLeft" else "",
            color = Color.White,
            fontWeight = FontWeight.W800,
            fontSize = 50.sp
        )

    }

}