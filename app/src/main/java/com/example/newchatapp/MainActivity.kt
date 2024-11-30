package com.example.newchatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newchatapp.googleSign.GoogleAuthUiClient
import com.example.newchatapp.screens.ChatsScreenUI
import com.example.newchatapp.screens.SignInScreenUI
import com.example.newchatapp.ui.theme.NewChatAppTheme
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: ChatViewModel by viewModels()

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            viewModel = viewModel,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewChatAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        val state by viewModel.state.collectAsState()
                        val navController = rememberNavController()
                        NavHost(navController = navController, startDestination = StartScreen) {


                            composable<StartScreen> {
                                LaunchedEffect(key1 = Unit) {
                                    val userData = googleAuthUiClient.getSignedInUser()
                                    if (userData != null) {
                                        viewModel.getUserData(userData.userId)
                                        navController.navigate(ChatsScreen)
                                    } else {
                                        navController.navigate(SignInScreen)
                                    }

                                }
                            }



                            composable<SignInScreen> {
                                val launcher =
                                    rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult(),
                                        onResult = { result ->
                                            if (result.resultCode == RESULT_OK) {
                                                lifecycleScope.launch {
                                                    val signInResult =
                                                        googleAuthUiClient.signInWithIntent(
                                                            intent = result.data ?: return@launch
                                                        )
                                                    viewModel.onSignInResult(signInResult)
                                                }


                                            }
                                        })


                                LaunchedEffect(key1 = state.isSignedIN) {
                                    val userData=googleAuthUiClient.getSignedInUser()
                                    userData?.run {
                                        viewModel.adduserToFirestore(userData)
                                        viewModel.getUserData(userData.userId)
                                        navController.navigate(ChatsScreen)
                                    }



                                }
                                SignInScreenUI(onSignInClick = {
                                    lifecycleScope.launch {
                                        val signIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signIntentSender ?: return@launch
                                            ).build()
                                        )
                                    }
                                })
                            }
                            composable<ChatsScreen> {
                                ChatsScreenUI(
                                    viewModel = viewModel,
                                    state = state
                                )
                            }

                        }

                    }


                }

            }
        }
    }
}
