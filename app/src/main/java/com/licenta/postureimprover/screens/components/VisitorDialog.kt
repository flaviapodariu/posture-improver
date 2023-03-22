package com.licenta.postureimprover.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun VisitorDialog(
    onClickAction: () -> Unit,
    dialogHeading: String,
    dialogText: String,
    buttonText: String,
    userInput: String,
    onInputChanged: (String) -> Unit
) {
    Dialog(onDismissRequest = { }) {
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
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
                        text = dialogHeading,
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
                        text = dialogText,
                        style = MaterialTheme.typography.bodyMedium,
                        softWrap = true
                    )

                }

                Spacer(modifier = Modifier.padding(top = 5.dp))
                VisibleTextInput(
                    text = userInput,
                    label = "Nickname",
                    onTextChanged = { onInputChanged(it) }
                )

                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp, bottom = 5.dp)
                ) {
                    Button(
                        onClick = { onClickAction() }
                    ) {
                        Text(text = buttonText)
                    }
                }
            }

        }
    }

}