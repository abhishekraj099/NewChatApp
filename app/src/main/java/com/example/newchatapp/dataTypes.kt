package com.example.newchatapp


data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val ppurl: String?,
    val email: String?
)

data class AppState(
    val isSignedIN: Boolean = false,
    val userData: UserData? = null,
    val signInError: String? = null,
    val srEmail: String = ""
)