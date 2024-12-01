package com.example.newchatapp.screens

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.newchatapp.AppState
import com.example.newchatapp.ChatUserData
import com.example.newchatapp.ChatViewModel
import com.example.newchatapp.Message

@PreviewLightDark
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatUI(
    viewModel: ChatViewModel=ChatViewModel(),
    userData: ChatUserData= ChatUserData(),
    chatId: String="",
    message: List<Message> = emptyList(),
    state: AppState = AppState(),
    onBack: () -> Unit={},
    context: Context = LocalContext.current

) {
    val tp = viewModel.tp
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = userData.ppurl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(40.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = userData.username.toString(),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                        if (userData.userId == tp.user1?.userId) {
                            AnimatedVisibility(tp.user1.typing) {
                                Text(text ="typing...", modifier = Modifier.padding(16.dp),
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.titleSmall,
                                    )

                            }
                        }
                        if (userData.userId == tp.user2?.userId) {
                            AnimatedVisibility(tp.user2.typing) {
                                Text(text ="typing...", modifier = Modifier.padding(16.dp),
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.titleSmall,
                                )

                            }
                        }
                    }
                }
            },
                navigationIcon = {
                     Icon(Icons.Filled.ArrowBackIosNew, contentDescription = null)
                }
            )

        },

    ) {
        it
    }
}