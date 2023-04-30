package com.licenta.postureimprover.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.licenta.postureimprover.R
import com.licenta.postureimprover.theme.AcidYellow
import com.licenta.postureimprover.theme.Purple40

@Composable
fun CameraTimer(seconds: Int, isTimerRunning: (Boolean) -> Unit) {
    var secondsLeft: Int by remember { mutableStateOf(seconds) }
    
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
        modifier = Modifier
            .fillMaxSize()
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

@Composable
fun TimerOptions(
    toggleTimerOptionsVisibilty: () -> Unit,
    setTimerSeconds: (Int) -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 50.dp, vertical = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.round_timer_40),
            contentDescription = "close timer options",
            modifier = Modifier.clickable {
                toggleTimerOptionsVisibilty()
            },
            tint = AcidYellow
        )

        Icon(
            painter = painterResource(id = R.drawable.timer_5s),
            contentDescription = "5s timer",
            modifier = Modifier.clickable {
                setTimerSeconds(5)
                toggleTimerOptionsVisibilty()
            },
            tint = AcidYellow
        )

        Icon(
            painter = painterResource(id = R.drawable.timer_10s),
            contentDescription = "10s timer",
            modifier = Modifier.clickable {
                setTimerSeconds(10)
                toggleTimerOptionsVisibilty()
            },
            tint = AcidYellow
        )

        Icon(
            painter = painterResource(id = R.drawable.timer_15s),
            contentDescription = "15s timer",
            modifier = Modifier.clickable {
                setTimerSeconds(15)
                toggleTimerOptionsVisibilty()
            },
            tint = AcidYellow
        )

    }
}