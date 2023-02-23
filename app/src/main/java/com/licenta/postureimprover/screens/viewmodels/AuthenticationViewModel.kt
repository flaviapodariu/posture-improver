package com.licenta.postureimprover.screens.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.licenta.postureimprover.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(): ViewModel() {
    var email: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    var confirmPassword: String by mutableStateOf("")
    private lateinit var auth: FirebaseAuth

    fun onEmailChanged(emailState: String) {
        email = emailState
    }

    fun onPasswordChanged(passwordState: String) {
        password = passwordState
    }

    fun onConfirmPasswordChanged(confirmPassState: String) {
        confirmPassword = confirmPassState
    }

    fun login(navController: NavHostController) {
        auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    navController.navigate(Routes.Dashboard.passArgs(email))
                } else {
                    Timber.tag("FIREBASE").d("sign in failed")
                }

            }

    }

    fun register(navController: NavHostController, context: Context) {
        auth = Firebase.auth
        try {

            if (password == confirmPassword && password.length >= 8) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            navController.navigate(Routes.Dashboard.passArgs(email))
                        }
                    }
            } else {
                Toast.makeText(
                    context,
                    "Passwords do not match!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } catch (e: FirebaseAuthUserCollisionException) {
            Timber.tag("FIREBASE").d("sign up failed: %s", e.stackTrace)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Timber.tag("FIREBASE").d("enter valid email")
        }
    }

}