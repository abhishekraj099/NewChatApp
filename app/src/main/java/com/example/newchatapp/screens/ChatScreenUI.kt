package com.example.newchatapp.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newchatapp.AppState
import com.example.newchatapp.ChatViewModel
import com.example.newchatapp.R
import com.example.newchatapp.dialogs.CustomDialogBox

@Preview(showSystemUi = true)
@Composable
fun ChatsScreenUI(viewModel: ChatViewModel = ChatViewModel(), state: AppState = AppState()) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = { viewModel.showDialog() },
            shape = RoundedCornerShape(50.dp),
            containerColor = colorScheme.inversePrimary
        ) {
            Icon(
                imageVector = Icons.Filled.AddComment,
                contentDescription = null,
                tint = Color.White
            )
        }

    }
    ) { it
        Image(
            painter = painterResource(id = R.drawable.blck_blurry),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        AnimatedVisibility(visible = state.showDialog) {
            CustomDialogBox(
                state = state,
                hideDialog = { viewModel.hideDialog() },
                addChat = {
                    viewModel.addChat(state.srEmail)
                    viewModel.hideDialog()
                    viewModel.setSrEmail("")
                },
                setEmail = {
                    viewModel.setSrEmail(it)
                }

            )

        }

    }
}