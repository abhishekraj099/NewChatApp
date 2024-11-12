package com.example.newchatapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel: ViewModel() {
    private val  _state= MutableStateFlow(AppState())
    val state= _state.asStateFlow()

    fun resetState() {
        // Reset the state of the app
    }

    fun onSignInResult(signInResult: SignInResult) {

    }
}