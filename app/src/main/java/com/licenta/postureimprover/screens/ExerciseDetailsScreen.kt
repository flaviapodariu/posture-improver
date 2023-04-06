package com.licenta.postureimprover.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.licenta.postureimprover.screens.viewmodels.ExerciseDetailsViewModel

@Composable
fun ExerciseDetailsScreen(
    exerciseDetailsViewModel: ExerciseDetailsViewModel = hiltViewModel()
) {
    val exercise = exerciseDetailsViewModel.exercise
    if(exercise != null) {

        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = exercise.imageUrl,
                contentDescription = exercise.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.4f)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = exercise.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center
                        )

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text= "* Insert targeted muscle",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )

                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Column(modifier = Modifier.fillMaxWidth()) {
                        val steps = exercise.description.trim().split(". ")
                        steps.forEachIndexed { stepNumber, instruction ->
                            Text(
                                text = "${stepNumber + 1}. $instruction",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(horizontal = 5.dp)
                            )
                            Spacer(Modifier.height(5.dp))
                        }
                    }
                }


            }
        }
    }


}