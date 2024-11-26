package com.example.newchatapp.screens

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showSystemUi = true)
@Composable
fun ChatsScreenUI() {
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {},
            shape = RoundedCornerShape(50.dp),
            containerColor = colorScheme.inversePrimary
        ) {
            Icon(imageVector = Icons.Filled.AddComment,
                contentDescription = null,
                tint = Color.White)
        }

    }
    ) {

    }
}