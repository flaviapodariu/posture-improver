package com.licenta.postureimprover.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.licenta.postureimprover.screens.viewmodels.TimerDialogViewModel


// really weird wrapper so that the card enters recomposition and updates its height
@Composable
fun FullTimerDialog(
    timerDialogViewModel: TimerDialogViewModel = hiltViewModel(),
    goToCameraScreen: () -> Unit,
    dialogStillShowing: (Boolean) -> Unit
) {
    if(timerDialogViewModel.getInstructionPrefs())
        TimerDialogItem(
            goToCameraScreen= goToCameraScreen,
            dialogStillShowing = dialogStillShowing
        )
    else goToCameraScreen()
}

@Composable
fun TimerDialogItem(
    timerDialogViewModel: TimerDialogViewModel = hiltViewModel(),
    goToCameraScreen: () -> Unit,
    dialogStillShowing: (Boolean) -> Unit
) {

    Dialog(onDismissRequest = {}) {
        Card(
            shape = RoundedCornerShape(15.dp), modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Camera action",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Spacer(modifier = Modifier.padding(top = 5.dp))
                Divider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp),

                    ) {
                    Text(
                        text = timerDialogViewModel.dialogText,
                        style = MaterialTheme.typography.bodyMedium,
                        softWrap = true
                    )
                }


                Spacer(modifier = Modifier.padding(top = 1.dp))

                if (timerDialogViewModel.showDialog3) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, top = 10.dp)
                    ) {
                        Checkbox(
                            checked = timerDialogViewModel.isChecked,
                            onCheckedChange = { timerDialogViewModel.onBoxSelected() },

                        )
                        Text(text = "Don't show this again")
                    }
                }
            }
            Column {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                ) {
                    if (!timerDialogViewModel.showDialog1) {
                        Button(onClick = { timerDialogViewModel.onBackPressed() }) {
                            Text(text = "Back")
                        }

                    }
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp)
                    ) {
                        Button(onClick = {
                            if (timerDialogViewModel.showDialog3) {
                                timerDialogViewModel.saveInstructionsPrefs()
                                dialogStillShowing(false)
                                goToCameraScreen()
                            } else timerDialogViewModel.onNextPressed()
                        }) {
                            Text(
                                text = if (timerDialogViewModel.showDialog3) "Open Camera" else "Next")
                        }
                    }


                }


            }
        }

    }

}