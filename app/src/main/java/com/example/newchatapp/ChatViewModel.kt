package com.example.newchatapp

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ChatViewModel : ViewModel() {
    private val _state = MutableStateFlow(AppState())
    val state = _state.asStateFlow()
    private val userCollection = Firebase.firestore.collection(USER_COLLECTION)

    fun resetState() {
        // Reset the state of the app
    }

    fun onSignInResult(signInResult: SignInResult) {
        _state.update {
            it.copy(
                isSignedIN = signInResult.data != null,
                signInError = signInResult.errorMessage,

                )
        }

    }

    fun adduserToFirestore(userData: UserData) {
        val userDataMap = mapOf(
            "userId" to userData?.userId,
            "username" to userData?.username,
            "ppurl" to userData?.ppurl,
            "email" to userData?.email
        )
        val userDocument = userCollection.document(userData.userId)
        userDocument.get().addOnSuccessListener {

            if (it.exists()) {
                userDocument.update(userDataMap).addOnSuccessListener {
                    Log.d(ContentValues.TAG, "User Data added to Firebase successfully")
                }.addOnFailureListener {
                    Log.d(ContentValues.TAG, "User Data added to Firebase Failed")
                }
            } else {
                userDocument.set(userDataMap).addOnSuccessListener {
                    Log.d(ContentValues.TAG, "User Data added to Firebase successfully")
                }.addOnFailureListener{
                    Log.d(ContentValues.TAG, "User Data added to Firebase Failed")
                }
            }
        }

    }
}