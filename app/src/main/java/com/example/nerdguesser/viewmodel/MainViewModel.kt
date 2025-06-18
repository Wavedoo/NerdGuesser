package com.example.nerdguesser.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//Taken from https://github.com/FirebaseExtended/make-it-so-android/tree/5c3b28c04f7582c7e60b30f3693d770b46819646/v2
//and modified
open class MainViewModel: ViewModel() {

    //Anything after a throwable does not execute.
    fun launchCatching(
        showMessageSnackbar: (String) -> Unit = {},
        showAuthErrorState: (Throwable) -> Unit = {},
        //Possibly make it so I take in a specific throwable and then that triggers error uistate
        block: suspend CoroutineScope.() -> Unit
    ) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                Log.d("Anime", "Throwable thrown: ${throwable.message}")
                //Stolen from Makeitso
                //Firebase.crashlytics.recordException(throwable)
                if (throwable is FirebaseAuthException) {
                    showAuthErrorState(throwable)
                    return@CoroutineExceptionHandler
                }
                val error = if (throwable.message.isNullOrBlank()) {
                    "Something went wrong."
                } else {
                    throwable.message!!
                }
                showMessageSnackbar(error)
            },
            block = block
        )
}