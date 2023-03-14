package com.licenta.postureimprover.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.licenta.postureimprover.theme.PurpleGrey40

@Composable
fun WorkoutScreen() {
    Row(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column() {
            Text(
                text = "Workouts",
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(right = 30.dp),
                fontSize = 35.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.End,
            )

            
            Canvas(modifier = Modifier
                .fillMaxSize()
                .height(20.dp)){
                val h = size.height
                val w = size.width

                drawLine(
                    start= Offset((w/4), 30f),
                    end = Offset(w, 30f),
                    brush = Brush.linearGradient(listOf(PurpleGrey40, PurpleGrey40)),
                    strokeWidth = 10f
                )
            }
        }

    }
}

@Composable
fun Calendar() {

}