package com.licenta.postureimprover.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.licenta.postureimprover.data.local.entities.ExerciseEntity
import com.licenta.postureimprover.screens.viewmodels.WorkoutViewModel
import com.licenta.postureimprover.theme.PurpleGrey40

@Composable
fun WorkoutScreen(
    workoutViewModel: WorkoutViewModel = hiltViewModel(),
    goToExerciseDetail: (exerciseId: String) -> Unit
) {

    LaunchedEffect(key1 = workoutViewModel.exerciseList) {
        workoutViewModel.getWorkout()
    }

    Row(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
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
                .fillMaxWidth()
                .height(20.dp)
            ) {
                val w = size.width

                drawLine(
                    start= Offset((w/4), 30f),
                    end = Offset(w, 30f),
                    brush = Brush.linearGradient(listOf(PurpleGrey40, PurpleGrey40)),
                    strokeWidth = 10f
                )
            }

            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {
                items(workoutViewModel.exerciseList, key= { it.id }) {
                    ExerciseItem(item = it, goToExerciseDetail = goToExerciseDetail)
                }
            }
        }
    }
}
@Composable
fun ExerciseItem(
    item: ExerciseEntity,
    goToExerciseDetail: (exerciseId: String) -> Unit) {

    Card(
        modifier= Modifier.clickable(
            enabled = true,
            onClick = {
                goToExerciseDetail("${ item.id }")
            }
        ),
        shape= RoundedCornerShape(40.dp),
        colors= CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .shadow(2.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(
                        text= item.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W400,
                    )

                    Spacer(modifier = Modifier.padding(top = 5.dp))

                    Text(
                        text= "${item.reps} reps",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = item.imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .width(100.dp)
                            .height(120.dp)
                            .padding(vertical = 2.dp),
                        contentScale = ContentScale.Fit
                    )

                }
        }

        }

    }
    Spacer(modifier = Modifier.padding(top = 5.dp))
}