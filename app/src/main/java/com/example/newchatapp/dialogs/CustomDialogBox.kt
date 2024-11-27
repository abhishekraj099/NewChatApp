package com.example.newchatapp.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.newchatapp.AppState

@Preview (showSystemUi = true)
@Composable
fun CustomDialogBox(
    state: AppState= AppState(),
    setEmail: (String) -> Unit={},
    hideDialog: () -> Unit={},
    addChat: () -> Unit={},
) {
    Dialog(
        onDismissRequest = { hideDialog },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = false,

            )
    ) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(.90f),
            colors = CardDefaults.cardColors(
                MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Column(
                modifier = Modifier.padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)

            ) {
                Text(
                    text = "Enter Email ID",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally)
                )
                OutlinedTextField(
                    label = {
                        Text(text = "Email ID")
                    },
                    value = state.srEmail,
                    onValueChange = {
                        setEmail(it)
                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    TextButton(onClick = { hideDialog() }) {
                        Text(
                            text = "Cancel",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error
                        )

                    }

                    TextButton(onClick = { addChat() }) {
                        Text(
                            text = "Add",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }

        }
    }
}